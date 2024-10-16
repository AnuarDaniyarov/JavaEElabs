package com.example.labSpring.Entity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class LabSpringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;
}
