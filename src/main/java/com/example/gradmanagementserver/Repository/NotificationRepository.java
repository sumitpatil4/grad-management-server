package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    public Notification findByUser(User user);

    @Transactional
    @Modifying
    @Query("delete from Notification n where n.notificationId =:notificationId")
    public void deleteUsingId(@Param("notificationId") Integer notificationId);

}
