package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MediaRepository extends JpaRepository<Image, Long> {
}
