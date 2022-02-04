package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserRepository repo;

    @GetMapping(" ")
    public String homePage() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/registering")
    public String registration(User user){
        repo.save(user);

        return "success";
    }


}
