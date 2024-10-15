package com.example.labSpring;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "lab_users")
public class User {
    @Min(value = 18, message = "Age must be greater than 18")
    private Long age;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(Long age, String name) {
        this.age = age;
        this.name = name;
    }

    public User() {

    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
