package com.example.viewWithSecurity.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Car {


        @Id
        private Long id;

        private String model;

        public Car(Long id, String model) {
            this.id = id;
            this.model = model;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car() {
    }
}

