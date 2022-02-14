package com.codeup.halfguard.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
//    UNIQUE is used because no 2 emails are the same
    private String username;

//    @Column(nullable = false, unique = true, length = 100)
////    UNIQUE is used because no 2 emails are the same
//    private String email;

    @Column(nullable = false, length = 64)
//    used 64 as length because we will use encoded password in an encrypted format
    private String password;

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public User(User copy) {
        id = copy.id;
        firstName = copy.firstName;
        lastName = copy.lastName;
        username = copy.username;
        password = copy.password;
//        email = copy.email;
    }

    public User() {
    }

    public User(String username, String password, List<Post> posts) {
        this.username = username;
//        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
//    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
