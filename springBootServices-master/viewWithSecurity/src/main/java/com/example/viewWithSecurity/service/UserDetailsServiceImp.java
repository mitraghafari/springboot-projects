package com.example.viewWithSecurity.service;

import com.example.viewWithSecurity.entity.Role;
import com.example.viewWithSecurity.entity.User;
import com.example.viewWithSecurity.exception.UserNotFoundException;
import com.example.viewWithSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {

        User user = userRepo.findByUsername(userName);
        if (user == null) throw new UserNotFoundException("USER NOT FOUND: "+userName); //it does not go to mvc layer, so

        Set<GrantedAuthority> grantedAuthoritySet=new HashSet<>();
        for(Role r : user.getRoleSet()){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(r.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthoritySet);
    }
}

