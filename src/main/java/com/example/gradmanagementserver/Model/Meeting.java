package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.sql.Time;
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
    private Time time;
    @Column(name = "meetingLink")
    private String meetingLink;
    @Column(name = "feedbackLink")
    private String feedbackLink;
    @Column(name = "assessmentLink")
    private String assessmentLink;

    @ManyToOne
    @JoinColumn(name = "topicId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trainer trainer;

//    @ManyToMany(mappedBy = "meetingList",cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Batch> batchList;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "meeting_batch",
            joinColumns = @JoinColumn(name = "meetingId"),
            inverseJoinColumns = @JoinColumn(name = "batchId"))
    private List<Batch> batchList;
}
