package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        // Check if username already exists
        try {
            if (userService.loadUserByUsername(user.getUsername()) != null) {
                model.addAttribute("registrationError", "Uživatelské jméno již existuje.");
                return "login";
            }
        } catch (Exception e) {
            // Username not found, proceed with registration
        }
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login?registered=true";
    }
}