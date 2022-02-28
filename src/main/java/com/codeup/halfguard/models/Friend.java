package com.codeup.halfguard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column
//    private String friendFirstName;
//
//    @Column
//    private String friendLastName;
//
//    @Column
//    private String friendUsername;

    @ManyToMany(mappedBy = "friends")
    private List<User> users;

//    @ManyToOne
//    @JoinColumn(name = "friend_id")
//    private User friendAdded;
//
//    @ManyToOne
//    @JoinColumn(name = "logged_In_id")
//    private User userAdding;

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

//    public User getFriendAdded() {
//        return friendAdded;
//    }
//
//    public void setFriendAdded(User friendAdded) {
//        this.friendAdded = friendAdded;
//    }
//
//    public User getUserAdding() {
//        return userAdding;
//    }
//
//    public void setUserAdding(User userAdding) {
//        this.userAdding = userAdding;
//    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //    public String getFriendFirstName() {
//        return friendFirstName;
//    }
//
//    public void setFriendFirstName(String friendFirstName) {
//        this.friendFirstName = friendFirstName;
//    }
//
//    public String getFriendLastName() {
//        return friendLastName;
//    }
//
//    public void setFriendLastName(String friendLastName) {
//        this.friendLastName = friendLastName;
//    }
//
//    public String getFriendUsername() {
//        return friendUsername;
//    }
//
//    public void setFriendUsername(String friendUsername) {
//        this.friendUsername = friendUsername;
//    }

}
