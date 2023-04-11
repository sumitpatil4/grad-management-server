package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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
    private Timestamp fromTime;
    @Column(name = "toTime")
    private Timestamp toTime;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    @JsonIgnore
    private Trainer trainer;
}
