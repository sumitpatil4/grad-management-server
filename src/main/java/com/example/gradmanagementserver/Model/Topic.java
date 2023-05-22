package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "topicId")
    private int topicId;
    @Column(name = "topicName")
    private String topicName;
    @Column(name = "isCompleted")
    private boolean isCompleted;
    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meeting> meetingList;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Scores> scoresList = new ArrayList<>();
}
