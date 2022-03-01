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

@OneToMany(cascade = CascadeType.ALL, mappedBy = "userJoining")
private List<Club> userJoiningClub;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "clubJoined")
private List<Club> clubBeingJoined;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAdding")
    private List<Friend> processOfAddingFriend;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendAdded")
    private List<Friend> friendAdded;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadingImage")
    private List<Image> uploadImage;

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



    public User(long id, String firstName, String lastName, String username, String password, long years, String beltRank, String location, String gymName, String profileImage, List<Post> posts, List<Club> userJoiningClub, List<Club> clubBeingJoined, List<Friend> processOfAddingFriend, List<Friend> friendAdded, List<Image> uploadImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.years = years;
        this.beltRank = beltRank;
        this.location = location;
        this.gymName = gymName;
        this.profileImage = profileImage;
        this.posts = posts;
        this.userJoiningClub = userJoiningClub;
        this.clubBeingJoined = clubBeingJoined;
        this.processOfAddingFriend = processOfAddingFriend;
        this.friendAdded = friendAdded;
        this.uploadImage = uploadImage;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }







    public List<Friend> getProcessOfAddingFriend() {
        return processOfAddingFriend;
    }

    public void setProcessOfAddingFriend(List<Friend> processOfAddingFriend) {
        this.processOfAddingFriend = processOfAddingFriend;
    }

    public List<Friend> getFriendAdded() {
        return friendAdded;
    }

    public void setFriendAdded(List<Friend> friendAdded) {
        this.friendAdded = friendAdded;
    }


    public List<Image> getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(List<Image> uploadImage) {
        this.uploadImage = uploadImage;
    }


    public List<Club> getUserJoiningClub() {
        return userJoiningClub;
    }

    public void setUserJoiningClub(List<Club> userJoiningClub) {
        this.userJoiningClub = userJoiningClub;
    }

    public List<Club> getClubBeingJoined() {
        return clubBeingJoined;
    }

    public void setClubBeingJoined(List<Club> clubBeingJoined) {
        this.clubBeingJoined = clubBeingJoined;
    }
}
