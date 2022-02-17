package com.codeup.halfguard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bios")
public class Bio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private int years;

    @Column(nullable = false, length = 100)
    private String rank;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 100)
    private String gymName;


    ////////////////////////////////////////////////////////////////////////////////////////////////////
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public Bio(){};

//    @ManyToMany(mappedBy = "bios")
//    private List<User> users;

    ////////////////////////////////////////////////////////////////////////////////////////////////////

//    public User getUser(){
//        return user;
//    }
//
//    public void setUser(User user){
//        this.user = user;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

}
