package com.example.labSpring.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LabSpringEntity {
    @Id
    private Long id;
    private String fullName;
    private String lastName;
    private String email;
    private int year;
}
