package com.cms.repository;

import com.cms.model.Userbadge;
import com.cms.model.UserbadgeId; // 1. Import the composite ID class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgeRepository extends JpaRepository<Userbadge, UserbadgeId> {

    List<Userbadge> findByUserID_Id(Long userId);

    List<Userbadge> findByBadgeID_Id(Long badgeId);

    List<Userbadge> findByUserID_IdOrderByDateEarnedDesc(Long userId);
}
