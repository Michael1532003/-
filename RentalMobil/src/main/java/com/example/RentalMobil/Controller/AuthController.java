package com.example.RentalMobil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RentalMobil.Entity.User;
import com.example.RentalMobil.Service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Username already exists");
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/mobils";
    }

}
