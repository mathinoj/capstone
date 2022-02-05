//package com.codeup.halfguard.controller;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.codeup.halfguard.models.User;
//import com.codeup.halfguard.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class HomeController {
////    @Autowired
////    private UserRepository repo;
//
//    private UserRepository userDao;
//    private PasswordEncoder passwordEncoder;
//
////    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
////    ***** INTELLIJ wants to make void *******
//public void UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
//    this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @GetMapping("")
//    public String homePage() {
//        return "index";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("user", new User());
//
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }
//
////    @PostMapping("/registering")
////    public String registration(User user){
////        repo.save(user);
////
////        return "success";
////    }
//
//
//}
