package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ClubRepository;
import com.codeup.halfguard.repositories.FriendRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendsController {
    private PostRepository postDao;
    private UserRepository userDao;
    private ClubRepository clubDao;
    private FriendRepository friendDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public FriendsController(PostRepository postDao, UserRepository userDao, ClubRepository clubDao, FriendRepository friendDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.clubDao = clubDao;
        this.friendDao = friendDao;
//        this.emailService = emailService;
    }


    @GetMapping("view_all_users")
    public String allUsers(Model model){
        List<User> showEveryoneThatUses = userDao.findAll();
        model.addAttribute("showEveryoneFromSite", showEveryoneThatUses);

        return "friends/all_users";
    }

    @GetMapping("/search_users")
    public String searchUsers(Model model, @Param("keyword") String keywordUser) {
        List<User> listAllUsers = userDao.findAll(keywordUser);
        model.addAttribute("listAllUsersToThePage", listAllUsers);
        model.addAttribute("keywordUser", keywordUser);

        return "friends/all_users";
    }
}
