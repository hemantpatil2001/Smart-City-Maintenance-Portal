package com.example.scmp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scmp.Model.Admin;
import com.example.scmp.Repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public boolean authenticateUser(String email, String password) {
        // Find user by email
        Admin admin = adminRepository.findByEmail(email);

        // Check if the user exists and the password matches
        return admin != null && admin.getPassword().equals(password);
    }
}
