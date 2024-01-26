package com.example.scmp.Model;

import jakarta.persistence.*;

@Entity
@Table(name="user_info")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;
    private String password;
    private String contact;
    private String city;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", contact="
                + contact + ", city=" + city + "]";
    }

    
    
}
