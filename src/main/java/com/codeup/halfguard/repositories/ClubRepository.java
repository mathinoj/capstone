package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository <Club, Long> {
    Club findById(long id);

    List<Club> findClubsByUser(User user);


}
