package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @PostMapping("/create/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createNewNotification(@PathVariable String userId, @RequestBody Notification notification){
        return notificationService.createNewNotification(notification,userId);
    }

    @GetMapping("/getNotifications")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getNotifications(){
        return notificationService.getNotifications();
    }

    @DeleteMapping("/deleteNotification/{notificationId}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteNotification(@PathVariable Integer notificationId){
        return notificationService.deleteNotification(notificationId);
    }
}
