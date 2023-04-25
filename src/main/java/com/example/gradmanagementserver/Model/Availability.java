package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "availabilityId")
    private int availabilityId;
    @Column(name = "date")
    private Date date;
    @Column(name = "fromTime")
    private Time fromTime;
    @Column(name = "toTime")
    private Time toTime;
    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trainer trainer;
}
