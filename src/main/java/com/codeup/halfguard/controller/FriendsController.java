package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Friend;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ClubRepository;
import com.codeup.halfguard.repositories.FriendRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("friends_homepage")
    public String friendsHomepage (Model model){

        return "/friends/homepage";
    }

    @GetMapping("view_all_users")
    public String allUsers(Model model){
        List<User> showEveryoneThatUses = userDao.findAll();
        //The line below shows all users
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

//    @GetMapping("/selected_friend_by_id_from_user")
////    public String addFriend(@PathVariable(name = "id") long id, @ModelAttribute User user, Model model){
//    public String addFriend(Model model){
//
////User clubCreated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        User clubGod = userDao.getById(clubCreated.getId());
//
//
//        User searchFiend = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User blahBlah = userDao.getById(searchFiend.getId());
//
//
//        model.addAttribute("newAddedFriend", new Friend());
//        model.addAttribute("letsSee", userDao.findByUsername(blahBlah));
//
////model.addAttribute("displaySpecificClub", clubDao.findClubsByUser(clubGod));
//        return "/friends/chosen_friend";
//    }



//    @PostMapping("add_friend")
    @PostMapping("/selected_friend_by_id_from_user")
    //, @RequestParam(name = "id") long id
    public String addFriendToDatabase(Model model){
        User foundFriend = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User selectFriend = userDao.getById(foundFriend.getId());

//        model.addAttribute("postSpecificUser", userDao.findById(editor.getId()));


        model.addAttribute("tryingThisOneOut", selectFriend);

        selectFriend.setId(selectFriend.getId());

        userDao.save(selectFriend);


//        return "redirect:friends_homepage";
        return "/friends/chosen_friend";

    }
}
