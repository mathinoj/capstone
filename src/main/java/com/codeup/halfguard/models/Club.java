package com.codeup.halfguard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String clubName;

    @Column(length = 100)
    private String clubLocation;

    @Column(length = 10000)
    private String clubDescription;

    @ManyToOne
    @JoinColumn(name="user_joining_club_id")
    private User userJoining;

    @ManyToOne
    @JoinColumn(name = "club_being_joined_id")
    private User clubJoined;

//    @ManyToMany(mappedBy = "clubs")
//    private List<User> users;

    public Club (){}

    public Club (long id, String clubName, String clubLocation, String clubDescription, User user){
        this.id = id;
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubDescription = clubDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public User getUserJoining() {
        return userJoining;
    }

    public void setUserJoining(User userJoining) {
        this.userJoining = userJoining;
    }

    public User getClubJoined() {
        return clubJoined;
    }

    public void setClubJoined(User clubJoined) {
        this.clubJoined = clubJoined;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

}
