package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
//    @Query("select u from User u where u.email = ?1")
//    User findByEmail(String email);

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
