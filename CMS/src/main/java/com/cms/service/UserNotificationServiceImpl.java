package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.UserNotification;
import com.cms.model.User;
import com.cms.repository.UserNotificationRepository;
import com.cms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserNotificationServiceImpl implements UserNotificationService {

    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;

    public UserNotificationServiceImpl(UserNotificationRepository userNotificationRepository, UserRepository userRepository) {
        this.userNotificationRepository = userNotificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserNotification> getAllNotifications() {
        return userNotificationRepository.findAll();
    }

    @Override
    public UserNotification getNotificationById(Long id) {
        return userNotificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserNotification", "id", id));
    }

    @Override
    public UserNotification createNotification(UserNotification notification) {
        if (notification.getUserID() == null || notification.getUserID().getId() == null) {
            throw new RuntimeException("User must be provided");
        }

        User managedUser = userRepository.findById(notification.getUserID().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        notification.setUserID(managedUser);              // assign managed entity
        notification.setCreatedDate(LocalDateTime.now()); // auto-fill created date

        return userNotificationRepository.save(notification);
    }



    @Override
    public UserNotification updateNotification(Long id, UserNotification notificationDetails) {
        UserNotification existing = userNotificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        // Preserve createdDate
        notificationDetails.setCreatedDate(existing.getCreatedDate());

        // Preserve user (or fetch managed user if needed)
        notificationDetails.setUserID(existing.getUserID());

        existing.setType(notificationDetails.getType());
        existing.setMessage(notificationDetails.getMessage());
        existing.setIsRead(notificationDetails.getIsRead());

        return userNotificationRepository.save(existing);
    }


    @Override
    public void deleteNotification(Long id) {
        UserNotification existing = getNotificationById(id);
        userNotificationRepository.delete(existing);
    }

    @Override
    public List<UserNotification> getNotificationsByUser(Long userId) {
        return userNotificationRepository.findByUserID_Id(userId);
    }

    @Override
    public List<UserNotification> getNotificationsByUserOrdered(Long userId) {
        return userNotificationRepository.findByUserID_IdOrderByCreatedDateDesc(userId);
    }

    @Override
    public List<UserNotification> getNotificationsByUserAndReadStatus(Long userId, Boolean isRead) {
        return userNotificationRepository.findByUserID_IdAndIsRead(userId, isRead);
    }

    @Override
    public List<UserNotification> getNotificationsByUserAndType(Long userId, String type) {
        return userNotificationRepository.findByUserID_IdAndType(userId, type);
    }
}
