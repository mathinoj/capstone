package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<User, Long> {
}
