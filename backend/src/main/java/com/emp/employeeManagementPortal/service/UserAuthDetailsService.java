package com.emp.employeeManagementPortal.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is invoked by DaoAuthenticationProvider.LoadUserByUsername
 * to check whether a user is there or not.
 * if found, then returns the UserAuth object
 */
@Service
public class UserAuthDetailsService implements UserDetailsService {

    private final UserAuthService userAuthService;

    public UserAuthDetailsService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  the username form field carries numeric userId
        int userId;
        try {
            userId = Integer.parseInt(username);
        } catch (NumberFormatException ex) {
            throw new UsernameNotFoundException("User ID must be numeric");
        }
        String encodedPassword = userAuthService.getEncodedPasswordOrNull(userId);
        if (encodedPassword == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return User.withUsername(username)
                .password(encodedPassword)
                .authorities(authorities)
                .build();
    }
}
