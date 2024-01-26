package com.example.scmp.Controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.scmp.Model.ComplaintModel;
import com.example.scmp.Model.User;
import com.example.scmp.Repository.ComplaintRepository;
import com.example.scmp.Repository.UserRepository;
import com.example.scmp.Service.AdminService;
import com.example.scmp.Service.ComplaintService;
import com.example.scmp.Service.UserService;

@Controller
public class AdminAuthController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String login(){
        return "admin_login";
    }    
    @GetMapping("/admin_login")
    public String admin_login(){
        return "admin_login";
    }  
    
    @PostMapping("/admin_login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        // Call the service to authenticate the user
        boolean isAuthenticated = adminService.authenticateUser(email, password);

        if (isAuthenticated) {
            // Redirect to a success page or perform other actions
            return "redirect:/admin_addUser";
        } else {
            // Add an error message and return to the login page
            String message = "Invalid username or password!";
            model.addAttribute("message", message);
            return "admin_login";
        }
    }

    //Admin Add Complaint Procedure Start
    @Autowired
    private ComplaintService imageService;

    // Add a Image UI
    @GetMapping("/admin_addComplaint")
    public String addImageUI(Model model) {
        model.addAttribute("image", new ComplaintModel());
        return "admin_addComplaint";
    }

    // Add a image API
    @PostMapping("/admin_addComplaint")
    public String addImage(@ModelAttribute ComplaintModel imageModel, @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException, IOException {
        imageService.addImage(imageModel, imageFile);
        return "redirect:/admin_addComplaint";
    }

    // Get all Images
    @GetMapping("/admin_viewComplaint")
    public String listImages(Model model) {
        List<ComplaintModel> images = imageService.getImages();
        model.addAttribute("images", images);
        return "admin_viewComplaint";
    }
    // Get Image using image IdD
    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {

        Optional<ComplaintModel> imageOp = imageService.getsImage(id);
        if (imageOp.isPresent()) {
            ComplaintModel image = imageOp.get();
            byte[] imageBytes = java.util.Base64.getDecoder().decode(image.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }

    }

    //Admin Add Complaint Procedure End

    //Delete Complaint from Admin View
    @Autowired
    private ComplaintRepository complaintRepository;

    @GetMapping("/deleteComplaint/{complaintId}")
    public String deleteComplaint(@PathVariable Long complaintId, Model model) {
        complaintRepository.deleteById(complaintId);
        return "redirect:/admin_viewComplaint";
    }


    @GetMapping("/updateComplaint/{complaintId}")
    public String updatePage(@PathVariable Long complaintId, Model model1)
    {
        Optional<ComplaintModel> complaint = complaintRepository.findById(complaintId);
        model1.addAttribute("complaint", complaint);
        
        return "admin_updateComplaint";
    }

    // @PostMapping("/updateComp")
    // public String updateComplaint(@ModelAttribute("complaint") ComplaintModel complaint) {
    //     //complaintRepository.updateComplaint(complaint);
    //     return "redirect:/admin_viewComplaint";
    
    // }
    

    @GetMapping("/viewuser")
    public String viewuser(Model model) {
        model.addAttribute("users",userRepository.findAll());
        return "admin_viewUser";
    }

    @GetMapping("/admin_addUser")
    public String addUser()
    {
        return "admin_addUser";
    }
    
    @PostMapping("/add-user")
    public String user_register(User usernew,Model model)
    {
        
        userRepository.save(usernew);
        System.out.println("Data Saved Successfully");
       
        model.addAttribute("users",userRepository.findAll());
        return "admin_addUser";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Integer userId, Model model) {
        userRepository.deleteById(userId);
        model.addAttribute("users",userRepository.findAll());
        return "admin_viewUser";
    }

    @GetMapping("/updateUser/{userId}")
    public String updateUserPage(@PathVariable Integer userId, Model model)
    {
        // Optional<User> user = userRepository.findById(userId);
        model.addAttribute("user", userRepository.findById(userId));
        
        return "admin_updateUser";
    }

    @Autowired
    private UserService userService;
    @PostMapping("/update-user")
    public String updateData(@RequestParam Integer id, @ModelAttribute User updatedEntity) {
        // Update the data in the database using your service
        userService.updateData(id, updatedEntity);
        return "redirect:/viewuser"; // Redirect to the list page
    }

}
