package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Club;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%"
            + " OR u.username Like %?1%")
//            + " OR u. Like %?1%")
        //WHERE " + "CONCAT (u.firstName, u.lastName, u.username)" + " LIKE %?1%") <--THIS IS THE FASTER WRITTEN
    public List<User> findAll(String keywordUser);


    <List>User findByUsername(User user);

    User findByUsername(String username);

    User findById(long id);

//    List<User> findByUserBio(User user);
//    <List>User findByUserBio(User user);


//    User getById(Long id);

//    User findByUsername(String username);

    List <User> findAllByIdNotLike(Long id);

//    You used 'u' because 'User'
//    Since email is unique we do NOT need to return a LIST like the ones below

//    @Query("select u from User u where u.firstName like %:term%")
//    List<User> findByFirstName(@Param("term") String firstName);

//    This makes query methods a little error-prone when refactoring regarding the parameter position. To solve this issue, you can use @Param annotation to give a method parameter a concrete name and bind the name in the query


//    @Query("select u from User u where u.lastName like %:term%")
//    List<User> findByLastName(@Param("term") String lastName);

//    This should just return a LIST of all users with the first/last name searched for



//    ***************OR TRY LIKE THESE BELOW*****************
//    @Query("select u from User u where u.firstName = ?1")
//    User findByFirstName(String firstName);

//    @Query("select u from User u where u.lastName = ?1")
//    User findByLastName(String lastName);
}
