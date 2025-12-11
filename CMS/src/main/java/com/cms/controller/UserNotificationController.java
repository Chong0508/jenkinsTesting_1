package com.cms.controller;

import com.cms.model.UserNotification;
import com.cms.service.UserNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user_notification")
@CrossOrigin(origins = "*")
public class UserNotificationController {
    private final UserNotificationService service;
    public UserNotificationController(UserNotificationService service) {
        this.service = service;
    }

    // 1. GET All Notifications
    @GetMapping
    public ResponseEntity<List<UserNotification>> getAllNotifications() {
        return ResponseEntity.ok(service.getAllNotifications());
    }

    // 2. GET Notification By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserNotification> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNotificationById(id));
    }

    // 3. CREATE Notification
    @PostMapping
    public ResponseEntity<UserNotification> createNotification(@RequestBody UserNotification notification) {
        return ResponseEntity.ok(service.createNotification(notification));
    }

    // 4. UPDATE Notification
    @PutMapping("/{id}")
    public ResponseEntity<UserNotification> updateNotification(@PathVariable Long id, @RequestBody UserNotification notificationDetails) {
        return ResponseEntity.ok(service.updateNotification(id, notificationDetails));
    }

    // 5. DELETE Notification
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        service.deleteNotification(id);
        return ResponseEntity.ok("User Notification with notificationID " + id + " deleted successfully.");
    }

    // 6. GET Notification By userID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserNotification>> getNotificationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getNotificationsByUser(userId));
    }

    // 7. GET Notification By userID ordered
    @GetMapping("/user/{userId}/ordered")
    public ResponseEntity<List<UserNotification>> getNotificationsByUserOrdered(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getNotificationsByUserOrdered(userId));
    }

    // 8. GET Notifications By userID and Read Status
    @GetMapping("/user/{userId}/filter/readStatus")
    public ResponseEntity<List<UserNotification>> getNotificationsByUserAndReadStatus(@PathVariable Long userId, @RequestParam Boolean isRead) {
        return ResponseEntity.ok(service.getNotificationsByUserAndReadStatus(userId, isRead));
    }

    // 9. GET Notifications By userID and Type
    @GetMapping("/user/{userId}/filter/type")
    public ResponseEntity<List<UserNotification>> getNotificationsByUserAndType(@PathVariable Long userId, @RequestParam String type) {
        return ResponseEntity.ok(service.getNotificationsByUserAndType(userId, type));
    }
}
