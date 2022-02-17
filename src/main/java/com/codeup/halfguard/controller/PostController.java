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
    private final PostRepository postDao;
    private final UserRepository userDao;
//    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
//        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String indexPosts(Model model) {
        model.addAttribute("allPosts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/userProfile")
    public String profile(Model model) {
//        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User postOwner = userDao.getById(loggedinUser.getId());


//        model.addAttribute("allPostsByUser", postDao.findAllBy(username));
//        User allPostings = postDao.findByUsername(username);
//        model.addAttribute("allPosters", allPostings);

//        Post post = postDao.getOne(id);
//        model.addAttribute("post", post);

//        Post post = postDao.getById(id);
//        model.addAttribute("post", post);

        return "posts/profile";
    }



    //    show a post by it's ID
    @GetMapping("/posts/{id}")
    public String showPost (Model model){
//        Post post = postDao.getById(id);
//        model.addAttribute("post", post);

        return "posts/display";
    }


    //EDIT A SINGLE POST ON -- all posts page ****************

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, String username, Model model) {
        Post postEdit = postDao.getById(id);
//        User postEdit = userDao.findByUsername(username);


        model.addAttribute("postEdit", postEdit);

        return "/posts/edit";
    }
    //EDIT A SINGLE POST ON -- all posts page ****************


    //TRYING TO GET ALL POSTS BY -- ONE USER
    @GetMapping("/posts/edit/")
    public String editPostByUser(@PathVariable long id, Model model) {
        Post getAllPostByUser = postDao.getById(id);
//        model.addAttribute("allPostsByUser", userDao.findAllById());
//        model.addAttribute("allUsers", userDao.findAll());

        User userIDFind = userDao.getOne(id);

//        Post postEdit = postDao.getById(id);

//        model.addAttribute("postEdit", postEdit);

        return "/posts/edited_page";
//        return "redirect:/posts/edit";
//        return "posts/edit";

    }


    // TRY TO EDIT A USERS POSTS
//    @PostMapping("/posts/edit/{id}")
    @PostMapping("/posts/edit/{id}")
    public String saveEditedPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {

        //Finds post by ID
//        Post postEdit = postDao.getById(id);
        Post postEdit = postDao.getOne(id);


        //Make changes to post
        postEdit.setTitle(title);
        postEdit.setBody(body);

        postDao.save(postEdit);

        //Redirect user to page after edit
        Post editedPost = postDao.getById(postEdit.getId());
        model.addAttribute("editedPost", editedPost);

//        return "redirect:/posts/edit";
//        return "redirect:/posts/edited_page";
        return "posts/edited_page";

    }


    @PostMapping("/posts/edit")
    public String saveEditPost(@RequestParam(name = "postTitle") String postTitle, @RequestParam(name = "postBody") String postBody, @RequestParam(name = "postId") long id) {

        Post postToEdit = postDao.getById(id);

        postToEdit.setBody(postBody);
        postToEdit.setTitle(postTitle);

        postDao.save(postToEdit);

        return "redirect:/posts";
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


    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
//        long deletePostId = id;
        postDao.deleteById(id);

        return "redirect:/posts";
    }


    ///CREATE POSTS ******************
    @GetMapping("/posts/create")
    public String displayCreatePost(Model model) {
        model.addAttribute("post", new Post());

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        User postCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User specificUser = userDao.getById(postCreator.getId());
////        User user = userDao.getOne(1L);

        post.setUser(specificUser);
        postDao.save(post);

        return "redirect:/posts";
    }
    ///CREATE POSTS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


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


}
