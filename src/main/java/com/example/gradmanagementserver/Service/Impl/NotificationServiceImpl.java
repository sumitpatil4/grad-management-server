package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.NotificationRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Map<String, Object> createNewNotification(Notification notification, String userId) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        Notification fetchedNotification = notificationRepository.findByUser(user);
        Notification newNotification = new Notification(
                notification.getNotificationId(),
                notification.getNotificationDesc(),
                new Timestamp(System.currentTimeMillis()),
                notification.getRequestedRole(),
                user
        );
        if(fetchedNotification==null) {
            notificationRepository.save(newNotification);
            response.put("message", "Notification created");
        }
        else{
            newNotification.setNotificationId(fetchedNotification.getNotificationId());
            notificationRepository.save(newNotification);
            response.put("message", "Notification updated");
        }
        response.put("notification",newNotification);
        return response;
    }

    @Override
    public Map<String, Object> getNotifications() {
        Map<String, Object> response = new HashMap<>();
        List<Notification> notificationList= notificationRepository.findAll();
        response.put("message","Notifications Fetched");
        response.put("notificationList",notificationList);
        return response;
    }
}
