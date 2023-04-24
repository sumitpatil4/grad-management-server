package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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
    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Batch> batchList;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Topic> topicList;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Intern> internList;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meeting> meetingList;
}
