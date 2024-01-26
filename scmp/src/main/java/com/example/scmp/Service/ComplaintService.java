package com.example.scmp.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.scmp.Model.ComplaintModel;
import com.example.scmp.Repository.ComplaintRepository;

import java.io.IOException;
import java.util.*;

@Service
public class ComplaintService {

    private final ComplaintRepository imageRepo;

    private ComplaintService(ComplaintRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    public List<ComplaintModel> getImages() {
        return imageRepo.findAll();
    }

    public ComplaintModel addImage(ComplaintModel imageModel, MultipartFile imageFile) throws IOException {
        imageModel.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
        return imageRepo.save(imageModel);
    }

    public Optional<ComplaintModel> getsImage(Long id) {
        return imageRepo.findById(id);
    }

    
}
