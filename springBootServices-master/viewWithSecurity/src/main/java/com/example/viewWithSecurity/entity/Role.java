package com.example.viewWithSecurity.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roleTbl")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long roleId;

    private String name;

    @ManyToMany(mappedBy = "roleSet")//mappedBy indicates the entity is the inverse of the relationship.
    Set<User> userSet;

    public Role() {
    }

    public Role(String name, Set<User> userSet) {
        this.name = name;
        this.userSet = userSet;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
