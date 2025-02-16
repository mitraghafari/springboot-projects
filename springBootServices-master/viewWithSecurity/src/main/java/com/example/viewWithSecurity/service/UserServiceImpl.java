package com.example.viewWithSecurity.service;

import com.example.viewWithSecurity.entity.User;
import com.example.viewWithSecurity.exception.UserExistException;
import com.example.viewWithSecurity.repository.RoleRepo;
import com.example.viewWithSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) throws UserExistException {

        User oldUser=findByUsername(user.getUsername());
        if(oldUser!=null)
            throw new UserExistException("A user with this name exist");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleSet(new HashSet<>(roleRepo.findAll()));
        user.setInsertDate(new Date());
        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
