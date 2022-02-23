package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ClubRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupsController {
    private PostRepository postDao;
    private UserRepository userDao;
    private ClubRepository clubDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public GroupsController(PostRepository postDao, UserRepository userDao, ClubRepository clubDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.clubDao = clubDao;
//        this.emailService = emailService;
    }


    @GetMapping("/groups_start")
    public String groupHomepage(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());

        //THIS WILL BE WHERE YOU PUT THE METHOD THAT BRINGS UP ALL THE GROUPS CREATED BY THE USER

        return "groups/groups";
    }

    @GetMapping("/groups_create")
    public String groupCreatePage(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());
        model.addAttribute("club", new Club());


        return "/groups/create_groups";
    }

//    ///CREATE POSTS ******************************************************************************************
//    @GetMapping("/posts/create")
//    public String displayCreatePost(Model model) {
//        model.addAttribute("post", new Post());
//
//        return "posts/create";
//    }

//    @PostMapping("/groups/create_groups")
    @PostMapping("/create_group_info")
    public String clubCreated(@ModelAttribute Club club){
        User clubCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User specificClubCreator = userDao.getById(clubCreator.getId());

//        club.setClubName(club.getClubName());
//        specificClubCreator.setClubs();
//        club.setClubLocation(club.getClubLocation());
//        club.setClubDescription(club.getClubDescription());


        specificClubCreator.setClubs(specificClubCreator.getClubs());


        clubDao.save(club);

        return "redirect:/groups_start";
//        return "/groups/groups";
    }


    @GetMapping("/group_should_display")
    public String showingAllGroups(Model model){
        User clubCreated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User clubGod = userDao.getById(clubCreated.getId());

        model.addAttribute("displaySpecificClub", userDao.findById(clubGod.getId()));

        return "/groups/groups";

    }


}
