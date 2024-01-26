package com.example.scmp.Model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "complaints_info")
@Getter
@Setter
/* @Builder */
@NoArgsConstructor
public class ComplaintModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    @Lob
    private String image;

    /*
     * @Lob
     * 
     * @Column(name = "image", unique = false, nullable = false, length = 100000)
     * private byte[] image;
     * 
     * public ImageModel() {
     * }
     * 
     * public ImageModel(Long id, String name, String type, byte[] image) {
     * this.id = id;
     * this.name = name;
     * this.type = type;
     * this.image = image;
     * }
     */

}

