package com.example.viewWithSecurity.service;

import com.example.viewWithSecurity.entity.User;
import com.example.viewWithSecurity.exception.UserExistException;


public interface UserService {



    void save(User user) throws UserExistException;

    User findByUsername(String username);
}
