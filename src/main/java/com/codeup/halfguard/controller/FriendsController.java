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
import org.springframework.web.servlet.ModelAndView;

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
    public String friendsHomepage(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLookingForFriend = userDao.getById(loggedInUser.getId());

//        model.addAttribute("showEveryUserFriend", friendDao.findFriendsById(userLookingForFriend.getId()));
        model.addAttribute("showEveryUserFriend", friendDao.findFriendByIdNotLike(userLookingForFriend.getId()));
//        model.addAttribute("showEveryUserFriend", friendDao.findFriendById(userLookingForFriend.getId()));
//        model.addAttribute("showEveryUserFriend", friendDao.findFriendById(userLookingForFriend.getId()));


        return "/friends/homepage";
    }

    @GetMapping("view_all_users")
    public String allUsers(Model model) {
        List<User> showEveryoneThatUses = userDao.findAll();
        //The line below shows all users
        model.addAttribute("showEveryoneFromSite", showEveryoneThatUses);
        model.addAttribute("revealAllUsers", showEveryoneThatUses);



        return "friends/all_users";
    }


    @GetMapping("/search_users")
    public String searchUsers(Model model, @Param("keyword") String keywordUser) {
        List<User> listAllUsers = userDao.findAll(keywordUser);
        model.addAttribute("listAllUsersToThePage", listAllUsers);
        model.addAttribute("keywordUser", keywordUser);

        return "friends/all_users";
    }


    ///////////////USE THIS ONE OKAY///////////////USE THIS ONE OKAY///////////////USE THIS ONE OKAY
    @GetMapping("/selected_friend_by_id_from_user/{id}")
    public String viewFriend(Model model,@PathVariable long id) {
        User userLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(userLoggedIn.getId());


        User user = userDao.getById(id);
        model.addAttribute("friendOfFriends", user.getProcessOfAddingFriend());
        model.addAttribute("friendOfFriends", user.getFriendAdded());

        return "/friends/chosen_friend";
//        return "redirect:/tryThisOne";
    }


        @PostMapping("tryThisOne/")
       public String processFriend(@RequestParam(name = "addedFriend") Long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            User userAddTheFriend = userDao.getById(loggedInUser.getId());
//////        User userAdditFriend = userDao.getById(id);
//
//
//            List<Friend> friendAdded = userAddTheFriend.getProcessOfAddingFriend();
//            friendAdded.add(friend);
//            userAddTheFriend.setProcessOfAddingFriend(friendAdded);
//            userDao.save(userAddTheFriend);



//THIS ADDS FRIEND TO USER ID TABLE, BUT WE NEED IT ASSIGNED TO THE FRIEND_ID PART OF THE TABLE
//            User userAdditFriend = userDao.getById(id);
//            List<Friend> friendAddedAgain = userAdditFriend.getFriendAdded();
//            friendAddedAgain.add(friend);
//            userAdditFriend.setFriendAdded(friendAddedAgain);
//            userDao.save(userAdditFriend);

//THIS IS THE ONE THAT WORKS THE WAY IT SHOULD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*************************
        User tryThisOneMoGen = userDao.getById(id);
        Friend newFriendZone = new Friend();
        newFriendZone.setFriendAdded(tryThisOneMoGen);
        newFriendZone.setUserAdding(loggedInUser);
        friendDao.save(newFriendZone);


//           return "/friends/homepage";
           return "redirect:/showFriendOnHomepage";

        }



    @GetMapping("/showFriendOnHomepage")
    public String showNewlyAddedFriendOnHomepage( String username, Model model, Friend friend) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLookingForFriend = userDao.getById(loggedInUser.getId());

//        model.addAttribute("showEveryUserFriend", friendDao.findFriendsById(userLookingForFriend.getId()));
        model.addAttribute("showEveryUserFriend", friendDao.findFriendByIdNotLike(userLookingForFriend.getId()));
//        model.addAttribute("showEveryUserFriend", friendDao.findFriendById(userLookingForFriend.getId()));
//        model.addAttribute("showEveryUserFriend", friendDao.findFriendById(userLookingForFriend.getId()));



//        model.addAttribute("showEveryUserFriend", friendDao.findAllById(userLookingForFriend.getId()));

//        model.addAttribute("showEveryUserFriend", friendDao.findById(userLookingForFriend.getId()));


//        List<Friend> friendlyList = friendDao.findAllByUserAdding(loggedInUser);
//        model.addAttribute("friend", friendlyList);
//        model.addAttribute("allUsers", userDao.findAllByIdNotLike(loggedInUser.getId()));
//        model.addAttribute("userLoggedIn", loggedInUser);
//        model.addAttribute("friends", new Friend());

//        return "/posts/edit";
        return "/friends/homepage";

    }


    @PostMapping("/friends/delete/{id}")
    public String deleteFriend(@PathVariable long id) {
//        long deletePostId = id;
        friendDao.deleteById(id);

//        return "/profile/userProfileEdited";
        return "redirect:/friends_homepage";
    }

}
