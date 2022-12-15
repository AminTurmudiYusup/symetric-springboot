package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Users {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String address;
    private String phone;//this is sensitiv data
    private String identityNumber;//this is sensitiv data
}
