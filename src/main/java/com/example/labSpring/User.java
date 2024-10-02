package com.example.labSpring;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class User {
    @Min(value = 18, message = "Age must be greater than 18")
    private Long age;
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public User(Long age, String name) {
        this.age = age;
        this.name = name;
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
}
