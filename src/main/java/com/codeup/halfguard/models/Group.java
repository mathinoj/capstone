
package com.codeup.halfguard.models;


import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 100)
    private String groupName;

    @Column(nullable = false, length = 10000)
    private String description;


//    @ManyToOne
//    @JoinColumn(name="group_id")
//    private Group group;
//
//    public Group getGroup(){
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }

    public Group(){}

    public Group(String location, String groupName, String description){
        this.location = location;
        this.groupName = groupName;
        this.description = description;
    }



//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
