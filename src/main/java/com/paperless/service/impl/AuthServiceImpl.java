package com.paperless.service.impl;

import com.paperless.common.constants.Constants;
import com.paperless.common.dto.EmailDetails;
import com.paperless.common.dto.JwtResponse;
import com.paperless.common.util.PlUtil;
import com.paperless.common.util.ResponseHandler;
import com.paperless.exception.PlException;
import com.paperless.exception.ErrorCode;
import com.paperless.model.TransactionLog;
import com.paperless.model.UserInfo;
import com.paperless.model.UserRole;
import com.paperless.repository.TransactionLogRepository;
import com.paperless.repository.UserInfoRepository;
import com.paperless.repository.UserRoleRepository;
import com.paperless.security.CustomUserDetailsService;
import com.paperless.security.JwtAuthenticationFilter;
import com.paperless.security.JwtTokenProvider;
import com.paperless.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${jwt.token.urlexpiration}")
    private Long urlExpiration;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRoleRepository userRole;

    @Autowired
    private JwtTokenProvider helper;

    @Autowired
    private CustomUserDetailsService detailsService;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private TransactionLogRepository transactionLogRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final int EXPIRATION_MINUTES = 1;

    public ResponseEntity<Object> getUserDetails(String username, String password) {

        Optional<UserInfo> userInfo = this.userInfoRepository.findByEmailIdAndPassword(username, passwordEncoder.encode(password));
        if (userInfo.isEmpty()) {
            return ResponseHandler.generateResponse("Please Enter Correct Login Details", HttpStatus.NOT_FOUND);
        } else
            return ResponseHandler.generateResponse("Login SuccessFully", HttpStatus.OK);
    }

    public String registerUser(UserInfo userInfo) {
        // Encode password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userInfo.setPassword(passwordEncoder.encode("123456"));

        userInfo.setActive(true); // Assuming new users are active by default
        userInfo.setEmailConfirm(false); // Assuming email confirmation is needed

        // Save the user to the database
        userInfoRepository.save(userInfo);

        return "User registered successfully";
    }




    @Override
    public ResponseEntity<Object> loginUser(String emailId, String password) {
        log.info("loginUser: Attempting to login with emailId: {}", emailId);
        try {
            Optional<UserInfo> userInfoOp = userInfoRepository.findByEmailId(emailId);

            if (userInfoOp.isPresent()) {
                UserInfo userInfo = userInfoOp.get();
                if (!userInfo.isActive()) {
                    log.info("loginUser: User is no longer active with userId {} and companyId: {}", userInfo.getId(), userInfo.getCompany().getId());
                    throw new PlException("User is no longer active with userId: " + userInfo.getId() + " and companyId " + userInfo.getCompany().getId(), ErrorCode.USER_NOT_ACTIVE, HttpStatus.NOT_FOUND);
                }

                // Verify the password
                if (passwordEncoder.matches(password, userInfo.getPassword())) {
                    log.info("loginUser: Password verified successfully for userId: {}", userInfo.getId());
                    String token = this.helper.generateToken(userInfo);

                    userInfo.setLast_login_date(LocalDateTime.now());
                    userInfoRepository.save(userInfo);
                    log.info("loginUser: Updated last login date for userId: {}", userInfo.getId());


                    JwtResponse response = JwtResponse.builder()
                            .jwtToken(token)
                            .username(userInfo.getUsername())
                            .userId(userInfo.getId()).build();
                    log.info("loginUser: Login successful for userId: {}", userInfo.getId());

                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    // Password does not match
                    log.warn("loginUser: Invalid password attempt for emailId: {}", emailId);
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
                }
            }

            // User not found
            log.warn("loginUser: User not found with emailId: {}", emailId);
            return ResponseEntity.notFound().build();
        }catch (PlException PlException) {
            log.error("loginUser: PlException occurred for emailId: {}, error message: {}", emailId, PlException.getMessage());
            throw PlException;
        } catch (Exception e) {
            log.error("loginUser: An unexpected error occurred for emailId: {}, error message: {}", emailId, e.getMessage());
            throw new PlException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR_WHILE_LOGGING_IN, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
        JwtResponse authResponse = null;
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = helper.getUsername(token);
        if (userEmail != null) {
            UserInfo user = userInfoRepository.findByEmailId(userEmail).get();
            if (helper.validateToken(token)) {
                var accessToken = helper.generateToken(user);
                authResponse = JwtResponse.builder()
                        .jwtToken(accessToken)
                        .username(userEmail)
                        .userId(user.getId())
                        .build();
            }

        }
        return ResponseEntity.ok().body(authResponse);
    }


    @Override
    public ResponseEntity<Object> upsertUser(UserInfo userInfo, UserInfo user) {

//        return userService.addUserBySuperAdminOrAdmin(userInfo, user);

        return  null;

    }

    @Override
    public ResponseEntity<?> createNewPassword(String password, Long userId, String email,String token) {
        try {
            Optional<UserInfo> existingUser = userInfoRepository.findByEmailIdAndId(email, userId);
            if (!existingUser.isPresent()) {
                log.warn("createNewPassword: User not found for email: {}", email);
                throw new PlException("User not found for email: " + email, ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            TransactionLog transactionLog = transactionLogRepository.findByEncryptedTransactionDataAndIsDeletedFalse(token);
            if (transactionLog == null) {
                log.warn("createNewPassword: Invalid token");
                throw new PlException("Password is already created, please send the reset link again", ErrorCode.INVALID_TOKEN, HttpStatus.BAD_REQUEST);
            }
            Date expireTime=new Date();
            TransactionLog existingTransactionTimeWithEmail = transactionLogRepository.
                    findByTransactionExpirationTimestampAndIsDeletedFalseAndChannelId
                            (transactionLog.getTransactionExpirationTimestamp(), transactionLog.getChannelId());
            if(existingTransactionTimeWithEmail.getTransactionExpirationTimestamp().before(expireTime)){

                log.warn("createNewPassword: Reset link is expired for token: {}", token);
                throw new PlException("Reset link is expired", ErrorCode.LINK_EXPIRED, HttpStatus.BAD_REQUEST);

            }
            UserInfo userInfo = existingUser.get();
            userInfo.setPassword(password);
            userInfo.setPasswordResetDate(LocalDateTime.now());
            // Mark the transaction as used
            transactionLog.setDeleted(true);
            transactionLog.setUpdatedDate(LocalDateTime.now());
            transactionLogRepository.save(transactionLog);
            userInfoRepository.save(userInfo);
            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        } catch (PlException PlException) {
            throw PlException;
        } catch (Exception e) {
            log.error("createNewPassword: error associated with userId: {},error message: {}", userId, e.getMessage());
            throw new PlException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR_WHILE_ADDING_USER, HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    @Override
    public ResponseEntity<?> requestPasswordReset(String userEmail, String templateName) {
        try {
            Optional<UserInfo> userOptional = userInfoRepository.findByEmailId(userEmail);
            if (userOptional.isPresent()) {
                UserInfo userInfo = userOptional.get();
                String resetToken = jwtTokenProvider.generateJwtTokenForResetPasswordUrl(userInfo.getId(), userEmail);

                // Log and save the transaction
//                transactionLogService.saveTransactionLogData(userInfo, urlExpiration, resetToken);

                String resetLink = Constants.resetLink + resetToken;
                sendEmailWithLink(userInfo, resetLink, templateName);

                return ResponseEntity.ok().body("Reset link sent to your email.");
            } else {
                log.warn("requestPasswordReset: User not found for email: {}", userEmail);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not registered");
            }
        } catch (PlException PlException) {
            log.error("requestPasswordReset: PlException occurred for email: {}, error message: {}", userEmail, PlException.getMessage());
            throw PlException;
        } catch (Exception e) {
            log.error("requestPasswordReset: Error occurred for email: {}, error message: {}", userEmail, e.getMessage());
            throw new PlException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR_WHILE_RESETTING_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }



    private void sendEmailWithLink(UserInfo userInfo, String resetLink,String templateName) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userInfo.getEmailId());
        emailDetails.setMsgBody(resetLink);
        try {
//            emailService.sendSimpleMail(emailDetails, templateName);
        } catch (Exception e) {
            log.error("sendEmailWithLink: error associated with userId: {},error message: {}", userInfo.getId(), e.getMessage());
            throw new PlException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR_WHILE_SENDING_EMAIL_LINK, HttpStatus.INTERNAL_SERVER_ERROR,e);

        }
    }

    private String generateResetTokenForUser(UserInfo userInfo) {
        try {
            String tokenData = userInfo.getId() + "&" + userInfo.getEmailId();
            return PlUtil.encrypt(tokenData);
        } catch (Exception e) {
            throw new RuntimeException("Error generating reset token", e);
        }
    }


//    @Override
//    public ResponseEntity<?> requestPasswordReset(String userEmail) {
//        Optional<UserInfo> userOptional = userInfoRepository.findByEmailId(userEmail);
//        if (userOptional.isPresent()) {
//            UserInfo userInfo = userOptional.get();
//            String resetToken = generateResetTokenForUser();
//            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
//            String resetTokenWithExpiry = resetToken + "|" + expiryTime.toString();
//            String resetLink = "https://advance-estimating-software.web.app/confirm-password?token=" + resetTokenWithExpiry;
//            EmailDetails emailDetails = new EmailDetails();
//            emailDetails.setRecipient(userInfo.getEmailId());
//            emailDetails.setSubject("Password Reset");
//            emailDetails.setMsgBody("Please reset your password by clicking on this link: " + resetLink);
//            try {
//                emailService.sendSimpleMail(emailDetails);
//                return ResponseEntity.ok().body("Reset link sent to your email.");
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send password reset link");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not registered");
//        }
//    }
//
//    // Generate a unique reset token
//    private String generateResetTokenForUser() {
//        return UUID.randomUUID().toString();
//    }


}

