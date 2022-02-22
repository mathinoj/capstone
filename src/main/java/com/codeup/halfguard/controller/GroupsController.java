package com.codeup.halfguard.controller;

import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupsController {
    private PostRepository postDao;
    private UserRepository userDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public GroupsController(PostRepository postDao, UserRepository userDao) {
        this.userDao = userDao;
        this.postDao = postDao;
//        this.emailService = emailService;
    }


    @GetMapping("/groups_start")
    public String groupHomepage(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());
        return "groups/groups";
    }

    @GetMapping("/groups_create")
    public String groupCreatePage(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());
        return "groups/create_groups";
    }

}
