package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface NotificationService {
    public ResponseEntity<?> createNewNotification(Notification notification, String userId);

    public ResponseEntity<?> getNotifications();

    public ResponseEntity<?> deleteNotification(Integer notificationId);
}
