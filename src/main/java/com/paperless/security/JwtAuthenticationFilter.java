package com.paperless.security;

import com.paperless.model.UserInfo;
import com.paperless.repository.UserInfoRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    try {
        // get JWT token from http request
        String token = getTokenFromRequest(request);
        log.info("doFilterInternal: Internal filtration is started for token: {}", token);
        // validate token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

            // get username from token
            String username = jwtTokenProvider.getUsername(token);

            // load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            log.warn("UserDetails not found for username: {}", username);
        }
    }
        filterChain.doFilter(request, response);
    } catch (Exception e) {
        log.error("Exception occurred while processing the JWT authentication filter", e);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}

    public UserInfo getUserFromHttpServletRequest(HttpServletRequest request) {
    try {
        String token = getTokenFromRequest(request);

        // Decode the JWT token and extract user information
        Claims claims = jwtTokenProvider.getAllClaimsFromToken(token);
        String userEmail = (String) claims.get("sub");
        Optional<UserInfo> userInfoOp = userInfoRepository.findByEmailId(userEmail);
        if (userInfoOp.isPresent()) {
            UserInfo userInfo = userInfoOp.get();
            return userInfo;
        }
    } catch (Exception e) {
        log.error("Exception occurred while extracting user from JWT token", e);
    }
        return null;
    }


    public String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7, bearerToken.length());
            log.info("getTokenFromRequest: token from httpServletRequest is fetched TOKEN: {}", token);
            return token;
        }
        return null;
    }

    public UserInfo getUserFromToken(String token) {
        try {
            // Decode the JWT token and extract user information
            Claims claims = jwtTokenProvider.getAllClaimsFromToken(token);
            String userEmail = (String) claims.get("sub");
            Optional<UserInfo> userInfoOp = userInfoRepository.findByEmailId(userEmail);
            if (userInfoOp.isPresent()) {
                UserInfo userInfo = userInfoOp.get();
                return userInfo;
            }
        } catch (Exception e) {
            log.error("getUserFromToken: error associated with token: {},error message: {}", token, e.getMessage());
        }
        return null;
    }

}
