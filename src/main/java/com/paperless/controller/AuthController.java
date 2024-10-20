package com.paperless.controller;


import com.paperless.common.constants.Constants;
import com.paperless.common.dto.JwtRequest;
import com.paperless.common.dto.PasswordResetReq;
import com.paperless.model.UserInfo;
import com.paperless.model.UserRole;
import com.paperless.security.JwtAuthenticationFilter;
import com.paperless.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@Tag(name = "[Authentication]", description = " API's related to authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


//    @GetMapping("login")
//    public ResponseEntity<Object> userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
//        return authService.getUserDetails(username, password);
//    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody JwtRequest request) {
        return Optional.ofNullable(request)
                .map(req -> authService.loginUser(req.getEmailId(), req.getPassword()))
                .orElse(ResponseEntity.badRequest().body("please enter login details"));

    }

//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody UserInfo userInfo, HttpServletRequest request){
//        UserInfo user = jwtAuthenticationFilter.getUserFromHttpServletRequest(request);
//        return authService.upsertUser(userInfo,user);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserInfo userInfo) {
        try {
            String responseMessage = authService.registerUser(userInfo);
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) throws IOException {

        if (request == null) {
            return null;
        }
        return authService.refreshToken(request);

    }

    @PostMapping("/create-password")
    public ResponseEntity<?> createNewPassword(@RequestBody PasswordResetReq resetReq, HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        UserInfo user = jwtAuthenticationFilter.getUserFromToken(token);
        return authService.createNewPassword(resetReq.getPassword(),user.getId(),resetReq.getEmail(),token);
    }

    @PostMapping("/request/password/{userEmail}")
    public ResponseEntity<?> requestEmail(@PathVariable String userEmail){
        return authService.requestPasswordReset(userEmail, Constants.OTP_TEMPLATE);
    }

//    @PostMapping("/forget-password")
//    public ResponseEntity<?> forgetPassword(@RequestBody PasswordResetReq resetReq,@PathVariable){
//        return authService.createNewPassword(resetReq.getPassword(),resetReq.getUserId(),resetReq.getEmail());
//    }
}