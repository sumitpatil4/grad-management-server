package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Notification;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface NotificationService {
    public Map<String,Object> createNewNotification(Notification notification,String userId);

    public Map<String,Object> getNotifications();

    public Map<String,Object> deleteNotification(Integer notificationId);
}
