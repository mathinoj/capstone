package com.codeup.halfguard.controller;

//import com.codeup.halfguard.models.Bio;

import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.ProfileRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.Paths;

@Controller
public class ProfileController {
    private ProfileRepository bioDao;
    private PostRepository postDao;
    private UserRepository userDao;

    @Value("${file-upload-path}")
    private String uploadPath;

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


    @PostMapping("/profile/biography")
    public String createBioNow(@ModelAttribute User user) {

        userDao.save(user);

        return "redirect:/userProfile";
    }


    ///////////////////////////////This takes user to edit biography page
    @GetMapping("/editBio")
    public String editBioForm(@ModelAttribute User user, Model model) {
        User specificUserBioEdit = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEdit.getId()); //ADDED THIS

        model.addAttribute("userSpecificBio", userEditBio);


        return "profile/edit_biography";
    }


    ///////////////////////////////This takes user edits and posts them to the database
    @PostMapping("/posts/userProfile")
//    @PostMapping("profile/edit_biography")
    public String processBioEdit(User user, Model model) {
        User specificUserBioEditing = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEditBio = userDao.getById(specificUserBioEditing.getId());

        userEditBio.setBeltRank(user.getBeltRank());
        userEditBio.setYears(user.getYears());
        userEditBio.setGymName(user.getGymName());
        userEditBio.setLocation(user.getLocation());

        userDao.save(userEditBio);


//        return "/profile/userProfileEdited";
//        return "/posts/profile"; //THIS WILL SHOW THE UPDATED YEARS BUT DOES NOT SHOW USER POSTS
//        return "redirect:/posts/userProfile"; //THIS MAKES CHANGES TO YEARS BUT DOES NOT DISPLAY, INSTEAD TAKES TO VIEW OF USER POSTS
//        return "/posts/userProfile"; //MAKES CHANGES IN TABLE, BUT GIVES WHITELABEL ERROR
//        return "/profile/userProfileEdited";
        return "redirect:/posts_afterEdits";
    }

    ////////////////////////////////////////THIS CONTROLLER SHOWS gets NEW EDITED BIO, AND SAME POSTS and displays-----------------
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





//////////////////////THIS IS SUPPOSED TO BE FOR UPLOADING PROFILE PIC - below is what happens in the terminal
//2022-02-23 16:35:29.960  WARN 29787 --- [nio-8080-exec-7] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MissingServletRequestParameterException: Required request parameter 'profileImage' for method parameter type String is not present]
/////////////////////ON FILESTACK WEBSITE IT SHOWS YOU HAVE 15 UPLOADS









    @GetMapping("/start_process_to_add_pic")
    public String takeToAddPicView(Model model) {
        User userPicUploadStart = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userPicUploading = userDao.getById(userPicUploadStart.getId());
        model.addAttribute("userProfileUploadButton", userPicUploading);


        return "/profile/profilePicture";
    }





    @PostMapping("/fileupload")
    public String postPicToTable(@RequestParam(name = "file") MultipartFile file, @ModelAttribute User user) {
        String filename = file.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);

        User userPicUploadStart = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userPicUploading = userDao.getById(userPicUploadStart.getId());

//        userPicUploading.setProfileImage(userPicUploading.getProfileImage());

//        userPicUploading.setImages(userPicUploading.getImages());
//        userPicUploading.setProfileImage(profileImage); THIS ONE WORKS

//        userPicUploading.setProfileImage(user.getProfileImage()); THIS WORKS


//        WORKED ON THIS YESTERDAY WITH JORDY, WAS LAST THING THAT NEEDS TO BE FIXED
//        userPicUploading.setProfileImage(file);

//        user.setProfileImage(userPicUploading.getProfileImage());


        userDao.save(userPicUploading);

//        return "posts/profile";
//        return "redirect:/posts/userProfile";
        return "redirect:/imageToGetMapping";
    }


    @GetMapping("/imageToGetMapping")
    public String profilePicSee(Model model) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userUploads = userDao.findById(loginUser.getId());
        model.addAttribute("user", userUploads);
        return "/profile/profilePicSee";
    }
}
