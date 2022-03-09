package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.*;
import com.codeup.halfguard.repositories.*;
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
    private MemberRepository memberDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public GroupsController(PostRepository postDao, UserRepository userDao, ClubRepository clubDao, FriendRepository friendDao, MemberRepository memberDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.clubDao = clubDao;
        this.friendDao = friendDao;
        this.memberDao = memberDao;
//        this.emailService = emailService;
    }


    @GetMapping("/groups_start")
    public String groupHomepage( Model model, Club club) {

        //  Post post = postDao.getById(id);
        //        model.addAttribute("post", post);

//        Club clubDisplay = clubDao.getById(id);
//        model.addAttribute("displaySpecificClub", clubDisplay);

        User userLoggedInAndReady = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User seeingOwnGroups = userDao.getById(userLoggedInAndReady.getId());

        model.addAttribute("displaySpecificClub", clubDao.findClubsByClubCreated(seeingOwnGroups));

//        model.addAttribute("postBySpecificUser", postDao.findPostsByUser(poster));


//        User clubCreated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User clubGod = userDao.getById(clubCreated.getId());
//        Club blahDoh = clubDao.getById(clubCreated.getId());

//        THE ONE BELOW IS HOW IT ORIGINALLY WAS WRITTEN BUT THEN YOU CREATED MEMBER And shit fucked up
//        model.addAttribute("displaySpecificClub", clubDao.findClubsByIdNotLike(clubCreated.getId()));


        //THIS WILL BE WHERE YOU PUT THE METHOD THAT BRINGS UP ALL THE GROUPS CREATED BY THE USER


        //THIS SHOULD SHOW ALL THE GROUPS ADDED BY USER
        User userAddingGroupToPage = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userThatAddedGroup = userDao.getById(userAddingGroupToPage.getId());

        model.addAttribute("showTheNewlyAddedGroup", memberDao.findMemberById(userThatAddedGroup.getId()));
//        model.addAttribute("showTheNewlyAddedGroup", clubDao.findClubsById(userThatAddedGroup.getId()));


        return "groups/groups";
    }

    @GetMapping("/groups_create")
    public String groupCreatePage(Model model) {

//        User userLoggedInCreating = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userCreatingGroup = userDao.getById(userLoggedInCreating.getId());
//        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());
        model.addAttribute("club", new Club());
//        model.addAttribute("club", userCreatingGroup.getId());


        return "/groups/create_groups";
    }

//    ///CREATE POSTS ******************************************************************************************


    //    @PostMapping("/groups/create_groups")
    @PostMapping("/create_group_info")
    public String clubCreated(@ModelAttribute Club club) {
        User clubCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User specificClubCreator = userDao.getById(clubCreator.getId());

//        club.setId(specificClubCreator.getId());
//        specificClubCreator.setClubs(specificClubCreator.getClubs());

        club.setClubCreated(specificClubCreator);



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

 //        THE ONE BELOW IS HOW IT ORIGINALLY WAS WRITTEN BUT THEN YOU CREATED MEMBER And shit fucked up
//        model.addAttribute("displaySpecificClubMaybe", clubDao.findClubsByUserJoining(clubGodRick));
        model.addAttribute("displaySpecificClubMaybe", clubDao.findClubsByIdNotLike(clubGodRick.getId()));


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
    @PostMapping("/join_group/{id}")
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

    @GetMapping("/selected_group_by_id_from_user/{id}")
    public String viewGroup(Model model, @PathVariable long id){
        User userAddingGroup = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(userAddingGroup);

        User user = userDao.getById(id);

        model.addAttribute("friendOfGroup", user.getClubBeingJoined());
        model.addAttribute("friendOfGroup", user.getUserJoiningClub());



        return "/groups/chosen_group";
    }



    @PostMapping("/join_group/")
//    @PostMapping("selected_group_by_id_from_user/{id}")
    public String processAddGroup (User user, @ModelAttribute Member member, @RequestParam(name = "addedGroup") long id){
        User userAddingGroup = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User tryingToJoinClub = userDao.getById(userAddingGroup.getId());

        User tryingToJoinClub = userDao.getById(id);
        Member newClubJoined = new Member();
        newClubJoined.setClubJoined(tryingToJoinClub);
        newClubJoined.setUserJoining(userAddingGroup);


        memberDao.save(newClubJoined);


//        return "redirect:/groups/groups";
        return "redirect:/showGroupsOnHomepage";
    }

    @GetMapping("/showGroupsOnHomepage")
    public String showNewAddedGroup(Model model){
        User userAddingGroupToPage = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userThatAddedGroup = userDao.getById(userAddingGroupToPage.getId());

        model.addAttribute("showTheNewlyAddedGroup", memberDao.findById(userThatAddedGroup.getId()));

//        return "/groups/groups";
        return "redirect:/groups_start";
    }



}
