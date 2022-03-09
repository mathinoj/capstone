package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long> {


}
