package com.cms.repository;

import com.cms.model.Userscore;
import com.cms.model.UserscoreId; // 1. Import the composite ID class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserScoreRepository extends JpaRepository<Userscore, UserscoreId> {

    List<Userscore> findByUserID_Id(Long userId);

    List<Userscore> findByBadgeID_Id(Long badgeId);

    Optional<Userscore> findByUserID_IdAndBadgeID_Id(Long userId, Long badgeId);

    List<Userscore> findByUserID_IdOrderByValueDesc(Long userId);
}
