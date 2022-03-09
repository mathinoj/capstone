package com.codeup.halfguard.models;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_joining_club_id")
    private User userJoining;

    @ManyToOne
    @JoinColumn(name = "club_being_joined_id")
    private User clubJoined;

    public Member(){}

    public Member(long id, User userJoining, User clubJoined) {
        this.id = id;
        this.userJoining = userJoining;
        this.clubJoined = clubJoined;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
