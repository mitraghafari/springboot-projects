package com.example.viewWithSecurity.entity;

import java.math.BigInteger;

public class Customer { //customer

    private BigInteger id;

    private String name;
    private String family;

    public Customer(){

    }

    public Customer(BigInteger id, String name, String family) {
        this.id = id;
        this.name = name;
        this.family = family;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
