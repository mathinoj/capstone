package com.codeup.halfguard.models;

import javax.persistence.*;

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
    private User user;

    public Club (){}

    public Club (long id, String clubName, String clubLocation, String clubDescription){
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
}
