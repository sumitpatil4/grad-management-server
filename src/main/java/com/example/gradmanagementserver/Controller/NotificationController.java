package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @PostMapping("/create/{userId}")
    public ResponseEntity<Object> createNewNotification(@PathVariable String userId, @RequestBody Notification notification){
        Map<String,Object> response = notificationService.createNewNotification(notification,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getNotifications")
    public ResponseEntity<Object> getNotifications(){
        Map<String,Object> response = notificationService.getNotifications();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
