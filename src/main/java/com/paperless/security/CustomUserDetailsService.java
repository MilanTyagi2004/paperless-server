package com.paperless.security;

import com.paperless.model.UserInfo;
import com.paperless.model.UserRole;
import com.paperless.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo currentUser = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user not found"));


        UserRole userRole = currentUser.getRole();

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(userRole.getName()));

        log.info("loadUserByUsername: Email ID of currentUser : {}", currentUser.getEmailId());
        log.info("loadUserByUsername: Authorities of currentUser : {}", authorities);


        return new org.springframework.security.core.userdetails.User(currentUser.getEmailId(), currentUser.getPassword(), authorities);
    }

}
