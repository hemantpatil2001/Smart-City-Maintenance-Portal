package com.example.scmp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scmp.Model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>  {
    User findByEmail(String email);
    Optional<User> findById(int id);
    void deleteById(Integer id);
}
