package com.codeup.halfguard.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    private String profile_picture;


//    @Column(nullable = true)
//    @Value("${file-upload-path}")
//    private String uploadPath;

//    @ManyToOne
//    private User user;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User uploadingImage;

    public Image() {
    }

//    public Image(Long id, String uploadPath, User uploadingImage) {
//        this.id = id;
////        this.uploadPath = uploadPath;
//        this.uploadingImage = uploadingImage;
//    }

    public Image(long id, String profile_picture, User uploadingImage) {
        this.id = id;
        this.profile_picture = profile_picture;
        this.uploadingImage = uploadingImage;
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getUploadPath() {
//        return uploadPath;
//    }
//
//    public void setUploadPath(String uploadPath) {
//        this.uploadPath = uploadPath;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public User getUploadingImage() {
        return uploadingImage;
    }

    public void setUploadingImage(User uploadingImage) {
        this.uploadingImage = uploadingImage;
    }


}
