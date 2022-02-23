package com.codeup.halfguard.services;

import com.codeup.halfguard.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public User loggedInUser() {
        return (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

