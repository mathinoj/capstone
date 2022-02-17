package com.codeup.halfguard.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(unique = true, length = 100)
//    UNIQUE is used because no 2 emails are the same
    private String username;


    @Column(length = 64)
//    used 64 as length because we will use encoded password in an encrypted format
    private String password;

//    @Column(nullable = false, length = 100)
//    private int years;
//
    @Column()
    private String rank;
//
//    @Column(nullable = false, length = 100)
//    private String location;
//
//    @Column(nullable = false, length = 100)
//    private String gymName;


    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        firstName = copy.firstName;
        lastName = copy.lastName;
        username = copy.username;
        password = copy.password;
//        years = copy.years;
        rank = copy.rank;
//        location = copy.location;
//        gymName = copy.gymName;
    }


    public User(String firstName, String lastName, String username, String password, String rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
//        this.years = years;
        this.rank = rank;
//        this.location = location;
//        this.gymName = gymName;
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


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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


//
//
//
//    public int getYears() {
//        return years;
//    }
//
//    public void setYears(int years) {
//        this.years = years;
//    }
//
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getGymName() {
//        return gymName;
//    }
//
//    public void setGymName(String gymName) {
//        this.gymName = gymName;
//    }


}
