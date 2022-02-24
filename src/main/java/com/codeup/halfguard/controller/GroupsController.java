package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ClubRepository;
import com.codeup.halfguard.repositories.FriendRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupsController {
    private PostRepository postDao;
    private UserRepository userDao;
    private ClubRepository clubDao;
    private FriendRepository friendDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public GroupsController(PostRepository postDao, UserRepository userDao, ClubRepository clubDao, FriendRepository friendDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.clubDao = clubDao;
        this.friendDao = friendDao;
//        this.emailService = emailService;
    }


    @GetMapping("/groups_start")
    public String groupHomepage(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());

        User clubCreated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User clubGod = userDao.getById(clubCreated.getId());
//        Club blahDoh = clubDao.getById(clubCreated.getId());

        System.out.println(clubDao.findClubsByUser(clubGod));
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


    //    @PostMapping("/groups/create_groups")
    @PostMapping("/create_group_info")
    public String clubCreated(@ModelAttribute Club club) {
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

    public String showingAllGroups(Model model) {
        User clubCreatedRader = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User clubGodRick = userDao.getById(clubCreatedRader.getId());
//                Club blahDoh = clubDao.getById(clubCreatedRader.getId());


        model.addAttribute("displaySpecificClubMaybe", clubDao.findClubsByUser(clubGodRick));


//        return "/groups/groups";
        return "redirect:/groups_start";

    }


    ///////////////////DELETE A GROUP------------------------------------------------------------------------------
    @PostMapping("/club/delete/{id}")
    public String deleteClub(@PathVariable long id) {
        clubDao.deleteById(id);

        return "redirect:/groups_start";
    }
///////////////////DELETE A GROUP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


///////////////////////////////////THIS SHOULD ISOLATE A SINGLE GROUP
//    @GetMapping("/club/{id}")
//    public String specificClub(@PathVariable long id, Model model){
//        Club club = clubDao.getById(id);
//
//        model.addAttribute("clubberLane", club);
//
//        return "groups/groups";
//    }

    ////////////////////////////////////THIS TAKES TO THE GROUP EDITOR PAGE
//    @GetMapping("/club/edit/{id}")
    @GetMapping("/club/edit/{id}")
    public String editClub(@PathVariable long id, Model model) {
        Club clubEdit = clubDao.getById(id);

        model.addAttribute("clubEdit", clubEdit);
//        model.addAttribute("id", clubEdit.getId());
        System.out.println(clubEdit);
        return "/groups/edit";
    }


    //    @PostMapping("/club/edit/now/{id}") GO TO group.html to find
    @PostMapping("/please_work")
    //, @PathVariable(name = "id") long id
    //@RequestParam(name = "id") long id)
    public String saveEditClub(@RequestParam(name = "clubName") String clubName, @RequestParam(name = "clubLocation") String clubLocation, @RequestParam(name="clubDescription") String clubDescription, @RequestParam(name = "id") long id){
//    public String saveEditClub(User user, Club club, @PathVariable long id) {
//        Club clubToEdit = clubDao.getById(id); FEB 24 1120
//        User clubEditPlease = userDao.getById(id);


//        clubToEdit.setClubName(clubToEdit.getClubName());FEB 24 1120
//        clubToEdit.setClubLocation(clubToEdit.getClubLocation());FEB 24 1120
//        clubToEdit.setClubDescription(clubToEdit.getClubDescription());FEB 24 1120

//        clubDao.save(clubToEdit);FEB 24 1120

        Club clubFebTwentyFour = clubDao.getById(id);
        clubFebTwentyFour.setClubName(clubName);
        clubFebTwentyFour.setClubLocation(clubLocation);
        clubFebTwentyFour.setClubDescription(clubDescription);
        clubFebTwentyFour.setId(id);
//        clubFebTwentyFour.setId(clubFebTwentyFour.getId());

        clubDao.save(clubFebTwentyFour);

        return "groups/edit_success";

    }


    /////////////////////////////////////////////////TAKES USER TO ALL CLUBS THAT ARE IN DATABASE
    @GetMapping("/view_all_clubs")
    public String allClubs(Model model) {
        List<Club> listClubbies = clubDao.findAll();
        model.addAttribute("listEveryClub", listClubbies);

        return "groups/searched_clubs";
    }


    ///////////////////////////////////Searches ALL clubs in datatbase and finds club according to user search
    @GetMapping("/search_clubs")
    public String searchClubs(Model model, @Param("keyword") String keyword) {
        List<Club> listClubs = clubDao.findAll(keyword);
        model.addAttribute("listAllClubs", listClubs);
        model.addAttribute("keyword", keyword);

        return "groups/searched_clubs";
    }


}
