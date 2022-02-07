package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
