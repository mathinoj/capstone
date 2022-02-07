package com.codeup.halfguard.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserRoles implements UserDetails {

    private User user;

    public UserRoles(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }

//    @Override
//    public String getFirstName(){
//        return user.getFirstName();
//    }
//
//    @Override
//    public String getLastName(){
//        return user.getLastName();
//    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

//    @Override
//    public String getEmail(){
//        return user.getEmail();
//    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }


}
