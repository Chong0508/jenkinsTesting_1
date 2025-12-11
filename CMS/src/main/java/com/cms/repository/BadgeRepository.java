package com.cms.repository;

import com.cms.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Optional<Badge> findBadgeById(Long id);

    Optional<Badge> findBadgeByName(String name);

    Optional<Badge> findBadgeByNameAndId(String name, Long id);

}
