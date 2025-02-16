package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Car {

    @Id
    private Long id;

    private String model;

    public Car(Long id, String model) {
        this.id = id;
        this.model = model;
    }
}
