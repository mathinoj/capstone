package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository <Friend, Long> {
}
