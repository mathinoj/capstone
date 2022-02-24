package com.codeup.halfguard.models;

import javax.persistence.*;
import java.util.Collection;
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
    private String username;

    @Column(length = 64)
    private String password;

    @Column(length = 100)
    private long years;

    @Column(length = 20)
    private String beltRank;

    @Column(length = 100)
    private String location;

    @Column(length = 100)
    private String gymName;

    @Column(length = 100)
    private String profileImage;

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        firstName = copy.firstName;
        lastName = copy.lastName;
        username = copy.username;
        password = copy.password;
        years = copy.years;
        beltRank = copy.beltRank;
        location = copy.location;
        gymName = copy.gymName;
        profileImage = copy.profileImage;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;



//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Club> clubs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="users_clubs",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "club_id")}
    )
    private List<Club> clubs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="users_friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private List<Friend> friends;


    public User() {
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public User(long years, String beltRank, String location, String gymName) {
//        List<Post> posts
        this.years = years;
        this.beltRank = beltRank;
        this.location = location;
        this.gymName = gymName;
//        this.posts = posts;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    public long getYears() {
        return years;
    }

    public void setYears(long years) {
        this.years = years;
    }

    public String getBeltRank() {
        return beltRank;
    }

    public void setBeltRank(String beltRank) {
        this.beltRank = beltRank;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
