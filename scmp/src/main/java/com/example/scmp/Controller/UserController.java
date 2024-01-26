package com.example.scmp.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.scmp.Model.ComplaintModel;
import com.example.scmp.Model.User;
import com.example.scmp.Repository.UserRepository;
import com.example.scmp.Service.ComplaintService;
import com.example.scmp.Service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/user_login")
    public String user_login()
    {
        return "user_login";
    }

    @PostMapping("/check_user")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        // Call the service to authenticate the user
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            // Redirect to a success page or perform other actions
            return "user_addComplaint";
        } else {
            // Add an error message and return to the login page
            String message = "Invalid username or password!";
            model.addAttribute("message", message);
            return "user_login";
        }
    }
    @GetMapping("/user_addComplaint")
    public String addImageUI(Model model) {
        model.addAttribute("image", new ComplaintModel());
        return "user_addComplaint";
    }

    // Add a image API
    @PostMapping("/user_addComplaint")
    public String addImage(@ModelAttribute ComplaintModel imageModel, @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException, IOException {
        complaintService.addImage(imageModel, imageFile);
        return "redirect:/user_addComplaint";
    }

    @GetMapping("/user_viewComplaint")
    public String listImages(Model model) {
        List<ComplaintModel> complaints = complaintService.getImages();
        model.addAttribute("complaints", complaints);
        return "user_viewComplaint";
    }



    @GetMapping("/register")
    public String user_register()
    {
        return "user_register";
    }

    @PostMapping("/register")
    public String user_register(User usernew,Model model)
    {
        
        userRepository.save(usernew);
        System.out.println("Data Saved Successfully");
       
        model.addAttribute("users",userRepository.findAll());
        return "user_login";
    }
}
