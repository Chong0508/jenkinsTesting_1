package com.cms.service;

import com.cms.model.UserNotification;

import java.util.List;

public interface UserNotificationService {

    List<UserNotification> getAllNotifications();

    UserNotification getNotificationById(Long id);

    UserNotification createNotification(UserNotification notification);

    UserNotification updateNotification(Long id, UserNotification notificationDetails);

    void deleteNotification(Long id);

    List<UserNotification> getNotificationsByUser(Long userId);

    List<UserNotification> getNotificationsByUserOrdered(Long userId);

    List<UserNotification> getNotificationsByUserAndReadStatus(Long userId, Boolean isRead);

    List<UserNotification> getNotificationsByUserAndType(Long userId, String type);
}
