package com.example.scmp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scmp.Model.User;
import com.example.scmp.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String email, String password) {
        // Find user by email
        User user = userRepository.findByEmail(email);

        // Check if the user exists and the password matches
        return user != null && user.getPassword().equals(password);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateData(Integer id, User updatedEntity) {
        User existingEntity = userRepository.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setName(updatedEntity.getName());
            existingEntity.setEmail(updatedEntity.getEmail());
            existingEntity.setPassword(updatedEntity.getPassword());
            existingEntity.setContact(updatedEntity.getContact());
            existingEntity.setCity(updatedEntity.getCity());
            // Update other fields as needed

            userRepository.save(existingEntity);
        }
    }
}
