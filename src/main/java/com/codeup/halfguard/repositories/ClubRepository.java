package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Member;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository <Club, Long> {
    @Query("SELECT c FROM Club c WHERE c.clubName LIKE %?1%"
    + " OR c.clubLocation LIKE %?1%"
    + " OR c.clubDescription Like %?1%")
    //WHERE " + "CONCAT (c.clubName, c.clubLocation, c.clubDescription)" + " LIKE %?1%") <--THIS IS THE FASTER WRITTEN CODE
    public List<Club> findAll(String keyword);
//use the % for containing the search
//1 is the first parameter in the method String keyword

    List<Club> findClubsByIdNotLike(long user);

    //THIS FINDS ALL CLUBS CREATED BY USER - IT IS USED FOR DISPLAYING USER MADE CLUBS ON groups_start HTML
    List<Club> findClubsByClubCreated(User user);


    Club findByClubName(String clubName);

//    List<Club> findClubsByClubCreated(long id);
    List<Club> findClubsById(long id);


}
