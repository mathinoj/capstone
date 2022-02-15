package com.codeup.halfguard.services;

import com.codeup.halfguard.models.User;
import com.codeup.halfguard.models.UserRoles;
import com.codeup.halfguard.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users){
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        User user = users.findByEmail(email);
        User users = users.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User unfounded");
        }
        return new UserRoles(user);
    }
}
