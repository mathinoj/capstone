package com.codeup.halfguard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "logged_In_user_id")
    private User userAdding;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friendAdded;


    public Friend (){}

//    public Friend(long id, User friendAdded, User userAdding) {
//        this.id = id;
//        this.friendAdded = friendAdded;
//        this.userAdding = userAdding;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //MIGHT NEED TO ADD DIS
//    public Friend(long id, User user) {
//        this.id = id;
//        this.user = user;
//    }


    public Friend(long id, User friendAdded, User userAdding) {
        this.id = id;
        this.friendAdded = friendAdded;
        this.userAdding = userAdding;
    }

    public User getFriendAdded() {
        return friendAdded;
    }

    public void setFriendAdded(User friendAdded) {
        this.friendAdded = friendAdded;
    }

    public User getUserAdding() {
        return userAdding;
    }

    public void setUserAdding(User userAdding) {
        this.userAdding = userAdding;
    }




}
