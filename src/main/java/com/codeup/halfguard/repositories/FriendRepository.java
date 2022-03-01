package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Friend;
import com.codeup.halfguard.models.Post;
import com.codeup.halfguard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository <Friend, Long> {
//    Friend findById(long id);

    <List>Friend findById(Friend friend);

    List<Friend> findFriendsById(Long userId);

    List<Friend> findFriendByIdNotLike(Long userId);

    List<Friend> findAllById(long userId);

//    List<Friend> findAllByUserAdding(User user);
//    List<Friend> findById(long userId);
}
