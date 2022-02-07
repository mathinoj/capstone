package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

//    @Autowired
//  this means we automatically create a new instance repository and inject that into the existing class
//    private UserRepository repo;

    private UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }


//    private UserRepository userDao;


    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String signupForm(Model model){
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(@ModelAttribute User user){
//        repo.save(user);
        userDao.save(user);


        return "/register_success";
    }

//    @PostMapping("/process_register")
//    public String saveUser(@ModelAttribute User user) {
//        userDao.save(user);
//
//        return "redirect:/register_success";
//    }

}
