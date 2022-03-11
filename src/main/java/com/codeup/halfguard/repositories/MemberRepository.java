package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Friend;
import com.codeup.halfguard.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository <Member, Long> {

//    List<Member> findMemberByClubJoinedAndId(long userId);
//    List<Member> findMemberByClubJoinedAndUserJoining(long userId);
List<Member> findMemberById(long id);
    List<Member> findMemberByUserJoiningId(long id);
    List<Member> findMemberByClubJoinedId(long id);

    //    List<Friend> findFriendByUserAddingId(long id);
}
