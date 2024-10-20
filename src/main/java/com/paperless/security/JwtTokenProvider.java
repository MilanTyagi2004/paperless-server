package com.paperless.security;

import com.paperless.exception.PlException;
import com.paperless.exception.ErrorCode;
import com.paperless.model.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String JwtSecret;

    @Value("${jwt.token.expiration}")
    private Long tokenExpiration;

    @Value("${jwt.token.urlexpiration}")
    private Long urlExpiration;

    // generate JWT token
    public String generateToken(UserInfo authentication) {
        log.info("generateToken: Started generating jwt token for user: {}", authentication.getEmailId());
        String username = authentication.getUsername();
        Long userId = authentication.getId();
        Long companyId = authentication.getCompany().getId();
        String companyName = authentication.getCompany().getCompanyName();
        Long roleId = authentication.getRole().getId();
        String roleName = authentication.getRole().getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + tokenExpiration);

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("companyId", companyId)
                .claim("companyName", companyName)
                .claim("roleId", roleId)
                .claim("roleName", roleName)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        log.info("generateToken: Generated jwt token for user: {} with token: {}", authentication.getEmailId(), token);
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(JwtSecret)
        );
    }

    // get username from Jwt token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    public Claims getAllClaimsFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // validate Jwt token
    public boolean validateToken(String token) {
        try {
            log.info("validateToken: validating the JWT token: {}", token);
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            log.info("validateToken: Successfully validated the JWT token: {}", token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("validateToken: Invalid JWT token: {} with error: ", token, ex);
            throw new PlException("Invalid JWT token", ErrorCode.INVALID_JWT_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException ex) {
            log.error("validateToken: Expired JWT token: {} with error: ", token, ex);
            throw new PlException("Expired JWT token", ErrorCode.EXPIRED_JWT_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException ex) {
            log.error("validateToken: Unsupported JWT token: {} with error: ", token, ex);
            throw new PlException("Unsupported JWT token", ErrorCode.UNSUPPORTED_JWT_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException ex) {
            log.error("validateToken: JWT claims string is empty: {} with error: ", token, ex);
            throw new PlException("JWT claims string is empty", ErrorCode.JWT_CLAIMS_STRING_IS_EMPTY, HttpStatus.UNAUTHORIZED);
        }
    }

    // generate JWT token
    public String generateJwtTokenForResetPasswordUrl(Long userInfoId,String email) {
        log.info("generateJwtTokenForResetPasswordUrl: Started generating jwt token for email: {}", email);
        Long userId = userInfoId;
        String userEmail=email;
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + urlExpiration);

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("userEmail", email)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        log.info("generateJwtTokenForResetPasswordUrl: Generated jwt token for user: {} with token: {}", email, token);
        return token;
    }
}
