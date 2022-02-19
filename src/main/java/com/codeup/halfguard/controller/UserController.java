package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;


    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }


    /////////////////////////////////TAKES TO REG PAGE TO "SIGNUP", THEN AFTER IT CONFIRMS REG SUCCESS
    @GetMapping("/register")
    public String signupForm(Model model){
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/process_registration")
    public String processRegistration(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);

//        User userCreate = userDao.getById(1L);
//        user.setUsername(userCreate.getUsername());
//        userDao.save(user);

        return "registration_success";
    }
    /////////////////////////////////TAKES TO REG PAGE TO "SIGNUP", THEN AFTER IT CONFIRMS REG SUCCESS ^^^^^^^^^^^



    /////////////////////////AFTER REG SUCCESS THIS TAKES THEM TO LOGIN

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("user", new User());

        return "users/login";
    }
    /////////////////////////AFTER REG SUCCESS THIS TAKES THEM TO LOGIN ^^^^^^^^^^^^^^^^^^^^^


//    @PostMapping("/signup")
//    public String saveUser(@ModelAttribute User user) {
////        String hash = passwordEncoder.encode(user.getPassword());
////        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }
    @PostMapping("/users/login")
    public String saveUser(@ModelAttribute User user) {
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/posts/index";
    }


    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<User> listUsers = userDao.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

//    @GetMapping("/posts_index")
//    public String viewUsersList(@ModelAttribute User users){
//        users.addAttribute("user", users);
//
//        return "posts/index";
//    }


//************************THIS TAKES US TO THE 'index html in "POSTS" folder under TEMPLATES"'***************
//    @GetMapping("/list_users")
//    public String viewPostPage(){
//        return "posts/index";
//    }
}
