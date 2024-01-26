package com.example.scmp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scmp.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByEmail(String email);
}
