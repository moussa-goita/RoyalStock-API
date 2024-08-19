package com.test.services;

import com.test.entities.Notification;
import com.test.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(int id) {
        return notificationRepository.findById(id);
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteById(int id) {
        notificationRepository.deleteById(id);
    }

    public void markAsRead(int id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(n -> {
            n.setRead(true);
            notificationRepository.save(n);
        });
    }

    public List<Notification> findUnread() {
        return notificationRepository.findByIsReadFalse();
    }

    //Methode pour envoyer notification
    public void sendNotification(long user, String message) {
        // Créer et enregistrer la notification dans la base de données
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setDateNotif(notification.getDateNotif());
        notificationRepository.save(notification);

        // Envoyer l'email
        emailService.sendSimpleMessage(String.valueOf(user), "Gestion  de Stock", message);
        notificationRepository.save(notification);
    }
}
