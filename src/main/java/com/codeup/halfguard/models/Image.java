package com.codeup.halfguard.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true)
    @Value("${file-upload-path}")
    private String uploadPath;

    @ManyToOne
    private User user;


    public Image() {
    }

    public Image(Long id, String uploadPath) {
        this.id = id;
        this.uploadPath = uploadPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

}
