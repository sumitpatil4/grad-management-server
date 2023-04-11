package com.example.gradmanagementserver.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notificationId")
    private int notificationId;
    @Column(name = "notificationDesc")
    private String notificationDesc;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "RequestedRole")
    private String requestedRole;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
