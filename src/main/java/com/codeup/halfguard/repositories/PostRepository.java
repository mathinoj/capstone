package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByUser(User user);

}
