package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "trainingId")
    private int trainingId;
    @Column(name = "trainingName")
    private String trainingName;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Batch> batchList;

    @OneToMany(mappedBy = "training")
    @JsonIgnore
    private List<Topic> topicList;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Intern> internList;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meeting> meetingList;
}
