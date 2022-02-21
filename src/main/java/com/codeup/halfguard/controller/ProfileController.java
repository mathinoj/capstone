package com.codeup.halfguard.controller;

//import com.codeup.halfguard.models.Bio;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ProfileRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private ProfileRepository bioDao;
    private PostRepository postDao;
    private UserRepository userDao;

    public ProfileController(ProfileRepository bioDao, PostRepository postDao, UserRepository userDao) {
        this.bioDao = bioDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }


    @GetMapping("/biography")
    public String indexPosts(Model model) {
        model.addAttribute("allPosts", postDao.findAll());

        User specificUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User poster = userDao.getById(specificUser.getId());


        return "/edit_biography";
//        return "/profile/edit_biography";
    }


    ////////////CREATE BIOGRAPHY ************************************************************************
//    @GetMapping("/profile/biography")
//    public String displayUserBio(Model model) {
//        model.addAttribute("newBio", new User());
//
//        return "profile/biography";
//    }
//
//
//
//    @PostMapping("/profile/biography")
//    public String createBio(@ModelAttribute User user) {
//
//        User bioCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User specificUserBio = userDao.getById(bioCreator.getId());
//
//        user.setUser(specificUserBio);
////        user.setPosts(postDao.findPostsByUser(specificUserBio));
//        bioDao.save(user);
//
//        return "redirect:/biography";
//    }
    ////////////////////////////////////////////////////////////////////////////^^^^^^^^^^^^^^^^^^^



    ////////////////////////////////////////////////////////////////////////////
//TOOK THIS OUT TO START THE ONE ON LINE 108
//    @GetMapping("/profile/biography")
//    public String showUserBio(Model model){
//        model.addAttribute("newUserBio", new User());
//
//        return "profile/biography";
//    }

    @PostMapping("/profile/biography")
    public String createBioNow(@ModelAttribute User user){

        userDao.save(user);

        return "redirect:/userProfile";
    }

////////////////////////////////////////////////////////////////////////////


    ///////////THIS TAKES USER TO THEIR PROFILE AND THEIR SPECIFIC POSTS -- profile.html***************
//    @GetMapping("/posts/userProfileEdit")
//    @GetMapping("/posts/userProfile")
//    public String profileBio(Model model) {
//        User specificUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        User userBio = userDao.getById(specificUser.getId());
//
//        model.addAttribute("userSpecificBio", specificUser);
//
//
////        model.addAttribute("postBySpecificUser", postDao.findPostsByUser(userBio));
////        model.addAttribute("userSpecificBio", userDao.getById(specificUser.getId()));
//
//        return "posts/profile";
//    }
    ///////////THIS TAKES USER TO THEIR PROFILE AND THEIR SPECIFIC POSTS -- profile.html^^^^^^^^^^^^^^^^^^










    @GetMapping("/editBio")
    public String editBioForm(@ModelAttribute User user, Model model){
        User specificUserBioEdit = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEdit.getId()); //ADDED THIS

        model.addAttribute("userSpecificBio", userEditBio);
//        model.addAttribute("addOnProfile", userDao.findByUsername(userEditBio));


        return "profile/edit_biography";
    }

//    @PostMapping("/process_edit")
    @PostMapping("/posts/userProfile")
//    @PostMapping("profile/edit_biography")
    public String processBioEdit(User user, Model model){
        User specificUserBioEdit = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEdit.getId());

        model.addAttribute("userSpecificBio", userEditBio);


        userEditBio.setBeltRank(user.getBeltRank());
        userEditBio.setYears(user.getYears());
        userEditBio.setGymName(user.getGymName());
        userEditBio.setLocation(user.getLocation());

        userDao.save(user);


//        return "/profile/userProfileEdited";
//        return "/posts/profile"; //THIS WILL SHOW THE UPDATED YEARS BUT DOES NOT SHOW USER POSTS
//        return "redirect:/posts/userProfile"; //THIS MAKES CHANGES TO YEARS BUT DOES NOT DISPLAY, INSTEAD TAKES TO VIEW OF USER POSTS
//        return "/posts/userProfile"; //MAKES CHANGES IN TABLE, BUT GIVES WHITELABEL ERROR
//        return "/profile/userProfileEdited";
        return "redirect:/posts_afterEdits";
    }

////////////////////////////////////////THIS CONTROLLER SHOWS NEW EDITED BIO, AND SAME POSTS----------------------------
    @GetMapping("/posts_afterEdits")
    public String profile(Model model) {

        //THIS PART SHOWS THE USERS NEWLY EDITED BIO
        User specificUserBio = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User editor = userDao.getById(specificUserBio.getId());

        model.addAttribute("postSpecificUser", userDao.findById(editor.getId()));



        ////THIS PART ADDS THE SPECIFIC USER POSTS TO THE PAGE
        User specificUserPosts = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User posterToNew = userDao.getById(specificUserPosts.getId());

        model.addAttribute("postBySpecificUserProfileEdited", postDao.findPostsByUser(posterToNew));


        return "/profile/userProfileEdited";
//                return "/posts/profile";
    }
////////////////////////////////////////THIS CONTROLLER SHOWS NEW EDITED BIO, AND SAME POSTS^^^^^^^^^^^^^^^^^^^^^^^^^^^


}
