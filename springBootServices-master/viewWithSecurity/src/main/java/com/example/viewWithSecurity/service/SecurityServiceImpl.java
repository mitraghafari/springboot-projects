package com.example.viewWithSecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImp")
    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {

        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (currentUser instanceof UserDetails) {
            return ((UserDetails) currentUser).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {

        UserDetails  userDetails=userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());

        authenticationManager.authenticate(token);
        if(token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            System.out.printf("auto login %s successful",username);
        }

    }
}
