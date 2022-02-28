package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
//import com.codeup.halfguard.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private PostRepository postDao;
    private UserRepository userDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.userDao = userDao;
        this.postDao = postDao;
//        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String indexPosts(Model model) {
//        model.addAttribute("allPosts", postDao.findAll());
        model.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }


    ///////////THIS TAKES USER TO THEIR PROFILE AND THEIR SPECIFIC POSTS -- profile.html***************
    @GetMapping("/posts/userProfile")
    public String profile(Model model) {
        User specificUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User poster = userDao.getById(specificUser.getId());


        model.addAttribute("postBySpecificUser", postDao.findPostsByUser(poster));


        //THIS PART SHOWS THE USERS CURRENT BIO
        User specificUserBio = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User bioShouldBeHere = userDao.getById(specificUserBio.getId());


        model.addAttribute("bioShouldBeHere", userDao.findById(bioShouldBeHere.getId()));

        return "posts/profile";
    }
    ///////////THIS TAKES USER TO THEIR PROFILE AND THEIR SPECIFIC POSTS -- profile.html^^^^^^^^^^^^^^^^^^


    //    show a post by it's ID
    @GetMapping("/posts/{id}")
    public String specificPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);

        return "posts/display";
    }


    //EDIT A SINGLE POST ON -- all posts page **************** **************** **************** ****************
    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, String username, Model model) {
        Post postEdit = postDao.getById(id);
//        User postEdit = userDao.findByUsername(username);


        model.addAttribute("postEdit", postEdit);

        return "/posts/edit";
    }

@PostMapping("/will_this_work")
public String editPostGoesHere(@RequestParam(name = "postTitle") String postTitle, @RequestParam(name = "postBody") String postBody, @RequestParam(name = "postId") long id){
        Post postToEdit = postDao.getById(id);

        postToEdit.setBody(postBody);
        postToEdit.setTitle(postTitle);

        postDao.save(postToEdit);

    return "redirect:/posts/userProfile";
}
    //EDIT A SINGLE POST ON -- all posts page ***************^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^




    /////////////////////THIS DELETES A SINGLE POST FROM THE USER PROFILE PAGE, TAKE THIS OUT AND DELETE WONT WORK  ****************
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
//        long deletePostId = id;
        postDao.deleteById(id);

//        return "/profile/userProfileEdited";
        return "redirect:/posts/userProfile";
//        return "/posts/profile"; //THIS MAKES CHANGES BUT DOESNT TAKE USER TO SEE CHANGES, INSTEAD ONLY SEE BUTTONS
    }
/////////////////////THIS DELETES A SINGLE POST FROM THE USER PROFILE PAGE, TAKE THIS OUT AND DELETE WONT WORK ^^^^^^^^^^^^^^^^^


    ///CREATE POSTS ******************************************************************************************
    @GetMapping("/posts/create")
    public String displayCreatePost(Model model) {
        model.addAttribute("post", new Post());

        return "posts/create";
    }

//    @GetMapping("/posts")
//    public String indexPosts(Model model) {
////        model.addAttribute("allPosts", postDao.findAll());
//        model.addAttribute("allPosts", postDao.findAll());
//        return "posts/index";
//    }


    //THIS ALLOWS FOR MULTIPLE POSTS BY ONE USER AND THE TABLE TO IDENTIFY THE USER ID ASSOCIATED WITH THE POST
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        User postCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User specificUser = userDao.getById(postCreator.getId());

        post.setUser(specificUser);
        postDao.save(post);

//        return "/posts/profile"; THIS POSTS ONTO TABLE BUT BRINGS USER TO POSTS/CREATE AND DOESN'T LIST CREATED POST
//        return "/posts/index"; // THIS CREATES POST BUT TAKES BACK TO INDEX PAGE AND DOES NOT SHOW ANY POSTS UNTIL YOU CLICK A LINK
//        return "redirect: /posts/profile"; THIS GIVES A WHITELABEL ERROR PAGE - BUT IT DOES STILL POST ONTO POST TABLE
        return "redirect:/posts/userProfile"; //THIS TAKES BACK TO PROFILE PAGE WITH ALL NEW UPDATES
    }
    ///CREATE POSTS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


}


//    @GetMapping("/posts/delete/{id}")
//    public String deletePost(@PathVariable long id, Model model) {
//        Post deletePost = postDao.deleteById(id);
//
//        model.addAttribute("postToEdit", deletePost);
//        return "redirect:/posts";
//    }

//    @PostMapping("/posts/delete/{id}")
//    public String deletePost(@PathVariable long id, Model model) {
//        long deletePost = id;
//        postDao.deleteById(id);
//
//        model.addAttribute("postToEdit", deletePost);
//        return "redirect:/posts/index";
//    }

//    @GetMapping("/posts/edit")
//    public String displayPosts(Model model){
//
//                model.addAttribute("user", new User());
//        model.addAttribute("post", new Post());
//
//        return "posts/edit";
//    }
//    @PostMapping("/posts/edit")
//    public String editorPost(@ModelAttribute Post post){
//        User postCreation = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userPoster = userDao.getById(postCreation.getId());
//
//        post.setUser(userPoster);
//        postDao.save(post);
//
//        return "redirect:post/edited_page";
//    }