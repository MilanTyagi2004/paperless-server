package com.paperless.service;

import com.paperless.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Object> getUserDetails(String username, String password);
    ResponseEntity<Object> loginUser(String username, String password);
    ResponseEntity<Object> refreshToken(HttpServletRequest request);
    ResponseEntity<?> createNewPassword(String password, Long userId, String email,String token);

    ResponseEntity<Object> upsertUser(UserInfo userInfo, UserInfo user);

    ResponseEntity<?> requestPasswordReset(String userEmail,String templateName);
    String registerUser(UserInfo userInfo);

}