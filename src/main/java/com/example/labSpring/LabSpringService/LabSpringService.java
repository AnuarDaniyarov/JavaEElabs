package com.example.labSpring.LabSpringService;

import com.example.labSpring.Entity.LabSpringEntity;
import com.example.labSpring.Repository.LabSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Service
public class LabSpringService {
    private LabSpringRepository labSpringRepository;

    @Autowired
    public LabSpringService(LabSpringRepository labSpringRepository) {
        this.labSpringRepository = labSpringRepository;
    }

    public LabSpringEntity createLabSpring(LabSpringEntity labSpring ){
        return labSpringRepository.save(labSpring);
    }

    public List<LabSpringEntity> getAllLabSpring(){
    return labSpringRepository.findAll();
    }

    public Optional<LabSpringEntity> getLabSpringById(Long id){
        return labSpringRepository.findById(id);
    }

    public LabSpringEntity updateLabSpring(Long id, LabSpringEntity labSpringDetails) {
        LabSpringEntity labSpring = labSpringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        labSpring.setName(labSpringDetails.getName());
        labSpring.setSurName(labSpringDetails.getSurName());
        labSpring.setAge(labSpringDetails.getAge());
        labSpring.setEmail(labSpringDetails.getEmail());
        return labSpringRepository.save(labSpring);
    }

    public void deleteLabSpring(Long id) {
        labSpringRepository.deleteById(id);
    }

}
