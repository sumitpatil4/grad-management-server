package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Scores")
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "scoreId")
    private int scoreId;

    @Column(name = "score")
    private int score;

    @Column(name = "totalScore")
    private int totalScore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Topic topic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Intern intern;
}
