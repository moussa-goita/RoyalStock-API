package com.test.controllers;

import com.test.entities.Notification;
import com.test.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        logger.info("GET /api/notifications");
        return notificationService.findAll();
    }

    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications() {
        logger.info("GET /api/notifications/unread");
        return notificationService.findUnread();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable int id) {
        logger.info("GET /api/notifications/{}", id);
        return notificationService.findById(id)
                .map(notification -> ResponseEntity.ok().body(notification))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable int id) {
        logger.info("PATCH /api/notifications/{}/read", id);
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        logger.info("POST /api/notifications");
        return notificationService.save(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable int id, @RequestBody Notification notificationDetails) {
        logger.info("PUT /api/notifications/{}", id);
        return notificationService.findById(id)
                .map(notification -> {
                    notification.setContenu(notificationDetails.getContenu());
                    notification.setDateNotif(notificationDetails.getDateNotif());
                    notification.setUtilisateur(notificationDetails.getUtilisateur());
                    Notification updatedNotification = notificationService.save(notification);
                    return ResponseEntity.ok().body(updatedNotification);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        logger.info("DELETE /api/notifications/{}", id);
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
