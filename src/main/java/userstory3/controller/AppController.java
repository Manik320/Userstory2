package userstory3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import userstory3.entity.Feedback;
import userstory3.entity.Template;
import userstory3.entity.User;
import userstory3.repository.UserRepository;
import userstory3.service.CustomUserDetailsService;
import userstory3.service.TemplateService;


import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TemplateService templateService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Validation errors occurred, return to the registration form with error messages
            return "signup_form";
        }

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepo.save(user);

            return "register_success";
        } catch (Exception e) {
            // Registration failed, handle the error and return to the registration form with an error message
            model.addAttribute("error", "Registration failed. Please try again.");
            return "signup_form";
        }
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/count")
    public String showUserCount(Model model) {
        long userCount = customUserDetailsService.getUserCount();
        model.addAttribute("userCount", userCount);
        return "user_count";
    }

    @GetMapping("/templates")
    public String showTemplates(Model model) {
        try {
            List<Template> templates = templateService.getAllTemplates();
            model.addAttribute("templates", templates);
            return "templates";
        } catch (Exception e) {
            // Handle error fetching templates and return to the templates page with an error message
            model.addAttribute("error", "Error fetching templates. Please try again.");
            return "templates";
        }
    }

    
}

    