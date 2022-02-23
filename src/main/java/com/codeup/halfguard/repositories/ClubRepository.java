package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository <Club, Long> {
}
