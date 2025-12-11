package com.cms.repository;

import com.cms.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    List<UserNotification> findByUserID_Id(Long userId);

    List<UserNotification> findByUserID_IdOrderByCreatedDateDesc(Long userId);

    List<UserNotification> findByUserID_IdAndIsRead(Long userId, Boolean isRead);

    List<UserNotification> findByUserID_IdAndType(Long userId, String type);
}
