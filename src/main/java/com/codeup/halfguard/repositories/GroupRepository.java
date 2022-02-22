package com.codeup.halfguard.repositories;

import com.codeup.halfguard.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository <Group, String> {
}
