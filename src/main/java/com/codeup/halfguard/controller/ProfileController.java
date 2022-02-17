package com.codeup.halfguard.controller;

//import com.codeup.halfguard.models.Bio;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import com.codeup.halfguard.repositories.BioRepository;
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
//        model.addAttribute("allPosts", postDao.findAll());

//        User specificUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User poster = userDao.getById(specificUser.getId());


        return "profile/biography";
    }


    ///CREATE POSTS ******************
    @GetMapping("/profile/biography")
    public String displayUserBio(Model model) {
//        model.addAttribute("bio", new Post());

        return "profile/biography";
    }

    @PostMapping("/profile/biography")
    public String createBio(@ModelAttribute User bio) {
//
//        User postCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User specificUser = userDao.getById(postCreator.getId());
//
//        bio.setUser(specificUser);
//        bioDao.save(bio);

        return "redirect:/biography";
    }

}
