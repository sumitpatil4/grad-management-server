package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "meetingId")
    private int meetingId;
    @Column(name = "meetingDesc")
    private String meetingDesc;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Timestamp time;
    @Column(name = "meetingLink")
    private String meetingLink;
    @Column(name = "feedbackLink")
    private String feedbackLink;
    @Column(name = "assessmentLink")
    private String assessmentLink;

    @ManyToOne
    @JoinColumn(name = "topicId")
    @JsonIgnore
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    @JsonIgnore
    private Training training;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    @JsonIgnore
    private Trainer trainer;

    @ManyToMany(mappedBy = "meetingList")
    @JsonIgnore
    private List<Batch> batchList;
}
