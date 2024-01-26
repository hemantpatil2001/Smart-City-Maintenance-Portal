package com.example.scmp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scmp.Model.ComplaintModel;

public interface ComplaintRepository extends JpaRepository<ComplaintModel, Long> {
    Optional<ComplaintModel> findByName(String name);
    Optional<ComplaintModel> findById(Long id);
    void deleteById(Long id);

    
    //void updateComplaint(ComplaintModel complaintModel);
}
