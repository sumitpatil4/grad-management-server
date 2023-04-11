package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;
    @Column(name = "RequestedRole")
    private String requestedRole;
    @OneToOne
    @JoinColumn(name = "userId")
    @Nullable
    @JsonManagedReference
    private User user;
}
