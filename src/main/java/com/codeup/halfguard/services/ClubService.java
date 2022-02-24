package com.codeup.halfguard.services;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.repositories.ClubRepository;
import com.codeup.halfguard.repositories.PostRepository;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    private ClubRepository clubDao;
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;

    public ClubService(PostRepository postDao, UserRepository userDao, ClubRepository clubDao) {
        this.clubDao = clubDao;
//        this.emailService = emailService;
    }


    public List<Club> listAll(String keyword) {
        if (keyword != null) {
            return clubDao.findAll(keyword);
        }
        return clubDao.findAll();
    }

}
