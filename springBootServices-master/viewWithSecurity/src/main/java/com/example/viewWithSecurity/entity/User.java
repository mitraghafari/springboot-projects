package com.example.viewWithSecurity.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "userTbl")
public class User {
    @Override
    public String toString() {
        return username+":"+password+":"+passConfirm+":"+insertDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String username;
    private String password;
    private Date insertDate;

    @Transient//do not persist
    private String passConfirm;

    @ManyToMany
    Set<Role> roleSet;

    public User() {
    }

    public User(String username, String password, Date insertDate, String passConfirm, Set<Role> roleSet) {
        this.username = username;
        this.password = password;
        this.insertDate = insertDate;
        this.passConfirm = passConfirm;
        this.roleSet = roleSet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
