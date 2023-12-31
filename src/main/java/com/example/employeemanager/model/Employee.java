package com.example.employeemanager.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String imageUrl;
    private String phone;
    @Column(nullable = false, updatable = false)
    private String employeeCode;
}
