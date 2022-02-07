package com.codeup.halfguard.models;

import com.codeup.halfguard.services.UserDetailsLoader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserRoles extends User implements UserDetails {
//    public UserRoles(User user) {
//        super(user);  // Call the copy constructor defined in User
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String roles = ""; // Since we're not using the authorization part of the component
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
//    }
//

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

    @Override
    public String getUsername() {
        return null;
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
