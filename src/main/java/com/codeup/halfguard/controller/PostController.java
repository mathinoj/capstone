package com.codeup.halfguard.controller;

import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import com.codeup.halfguard.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String indexPosts(Model model){
        model.addAttribute("allPosts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        Post singlePost = postDao.getById(id);
        model.addAttribute("singlePost", singlePost);

        return "posts/display";
    }


    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model){
        Post editPost = postDao.getById(id);

        model.addAttribute("postToEdit", editPost);

        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String saveEdit(@RequestParam(name="postTitle") String postTitle, @RequestParam(name="postBody") String postBody, @RequestParam(name="postId") long id){
        Post postToEdit = postDao.getById(id);

        postToEdit.setTitle(postTitle);
        postToEdit.setBody(postBody);

        postDao.save(postToEdit);

        return "redirect:/posts";
    }


    @PostMapping("/posts/delete/{id}")
    public String deletPost(@PathVariable long id){
        long deletePostId = id;
        postDao.deleteById(deletePostId);

        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String displayCreatePost(Model model){
        model.addAttribute("post", new Post());

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User postCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(userDao.getById(2L));
        post.setUser(postCreator);

        String emailSubject = post.getUser().getUsername() + ", post has been created!";

        String emailBody = "Congrats your latest post is up: " + post.getTitle() + post.getBody();

        emailService.preparAndSend(post, emailSubject, emailBody);
        postDao.save(post);

        return "redirect:/posts";
    }
}
