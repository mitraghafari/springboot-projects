package com.example.viewWithSecurity.repository;

import com.example.viewWithSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User,Integer> {

    User findByUsername(String name);

}
