package com.example.labSpring.Repository;

import com.example.labSpring.Entity.LabSpringEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabSpringRepository extends JpaRepository<LabSpringEntity, Long> {
    Page<LabSpringEntity> findByNameContaining(String name, Pageable pageable);
}

