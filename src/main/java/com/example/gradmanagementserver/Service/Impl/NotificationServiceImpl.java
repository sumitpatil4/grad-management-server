package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.NotificationRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public ResponseEntity<?> createNewNotification(Notification notification, String userId) {
        Map<String, Object> response = new HashMap<>();
        User user;
        Notification newNotification;
        Notification fetchedNotification;
        try{
            user = userRepository.findById(userId).get();
            fetchedNotification = notificationRepository.findByUser(user);

            String[] arr=notification.getRequestedRole().split("_");
            String subject= "Request From "+user.getUName()+" for "+arr[1];
            String text="Hello Admin,\n\nHope This email finds you well.\n\n"+notification.getNotificationDesc()+"\n\n\nThanks & Regards,\n"+user.getUName()+"\n";
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("saikrupananda21@gmail.com");
            message.setTo("sumit.patil@accolitedigital.com");
            message.setSubject(subject);
            message.setText(text);
            message.setReplyTo(user.getEmail());
            mailSender.send(message);

            System.out.println("Mail Sent Successfully");

            if(fetchedNotification==null) {
                newNotification = new Notification();
                newNotification.setNotificationDesc(notification.getNotificationDesc());
                newNotification.setTimestamp(new Timestamp(System.currentTimeMillis()));
                newNotification.setRequestedRole(notification.getRequestedRole());
                newNotification.setUser(user);
                notificationRepository.save(newNotification);
                response.put("message", "Notification created");
                response.put("notification",newNotification);
            }
            else{
                fetchedNotification.setNotificationDesc(notification.getNotificationDesc());
                fetchedNotification.setRequestedRole(notification.getRequestedRole());
                fetchedNotification.setTimestamp(new Timestamp(System.currentTimeMillis()));
                notificationRepository.save(fetchedNotification);
                response.put("message", "Notification updated");
                response.put("notification",fetchedNotification);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getNotifications() {
        Map<String, Object> response = new HashMap<>();
        List<Notification> notificationList;
        try {
            notificationList = notificationRepository.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Notifications Fetched");
        response.put("notificationList",notificationList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteNotification(Integer notificationId) {
        Map<String, Object> response = new HashMap<>();
        try{
            notificationRepository.deleteUsingId(notificationId);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Notification Deleted");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
