package com.codeup.halfguard.controller;

//import com.codeup.halfguard.models.Bio;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.BioRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private BioRepository bioDao;
    private PostRepository postDao;
    private UserRepository userDao;

    public ProfileController(BioRepository bioDao, PostRepository postDao, UserRepository userDao) {
        this.bioDao = bioDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }


    @GetMapping("/biography")
    public String indexPosts(Model model) {
        model.addAttribute("allPosts", postDao.findAll());

        User specificUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User poster = userDao.getById(specificUser.getId());


        return "profile/biography";
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
    public String signupForm(@ModelAttribute User user, Model model){
        User specificUserBioEdit = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEdit.getId()); //ADDED THIS

        model.addAttribute("userSpecificBio", userEditBio);

        return "profile/biography";
    }

    @PostMapping("/process_edit")
    public String processRegistration(User user){
        User specificUserBioEdit = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEdit.getId());

        userEditBio.setBeltRank(user.getBeltRank());
        userEditBio.setYears(user.getYears());
        userEditBio.setGymName(user.getGymName());
        userEditBio.setLocation(user.getLocation());

        userDao.save(user);


        return "redirect:/profile";
    }





}
