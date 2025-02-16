package com.example.viewWithSecurity.repository;

import com.example.viewWithSecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
