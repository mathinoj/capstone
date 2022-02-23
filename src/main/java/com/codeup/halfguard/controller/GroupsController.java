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

        User clubCreated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User clubGod = userDao.getById(clubCreated.getId());
        Club blahDoh = clubDao.getById(clubCreated.getId());


        model.addAttribute("displaySpecificClub", clubDao.findClubsByUser(clubGod));


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
//        Club blah = clubDao.getById(clubCreator.getId());


        club.setUser(specificClubCreator);

        //YOU CAN ALSO FALL BACK TO THESE 3 - these update the same line of the club, but does not add a new club
//        club.setId(blah.getId()); <---DIDNT NEED THIS
//        blah.setClubName(blah.getClubName());
//        blah.setClubLocation(blah.getClubLocation());

//        club.setId(specificClubCreator.getId()); FALL BACK TO THESE TWO
//        specificClubCreator.setClubs(specificClubCreator.getClubs());


        clubDao.save(club);

        return "redirect:/groups_start";
//        return "/groups/groups";
    }


//    @GetMapping("/group_should_display")
@GetMapping("/group/groups")

    public String showingAllGroups(Model model){
        User clubCreatedRader = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User clubGodRick = userDao.getById(clubCreatedRader.getId());
                Club blahDoh = clubDao.getById(clubCreatedRader.getId());


        model.addAttribute("displaySpecificClubMaybe", clubDao.findClubsByUser(clubGodRick));




//        return "/groups/groups";
    return "redirect:/groups_start";

    }


}
