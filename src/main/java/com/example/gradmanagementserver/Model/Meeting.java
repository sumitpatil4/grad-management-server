package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Column(name = "fromTime")
    private Time fromTime;
    @Column(name = "toTime")
    private Time toTime;
    @Column(name = "meetingLink")
    private String meetingLink;
    @Column(name = "feedbackLink")
    private String feedbackLink;
    @Column(name = "assessmentLink")
    private String assessmentLink;
    @Column(name = "eventId")
    private String eventId;
    @Column(name = "availabilityUsed")
    private Integer availabilityUsed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Topic topic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainingId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainerId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trainer trainer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "meeting_batch",
            joinColumns = @JoinColumn(name = "meetingId"),
            inverseJoinColumns = @JoinColumn(name = "batchId"))
    private List<Batch> batchList;

    @OneToMany(mappedBy = "meeting",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendanceList = new ArrayList<>();
}
