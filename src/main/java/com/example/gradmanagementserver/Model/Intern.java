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
@Table(name = "Intern")
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "internId")
    private int internId;
    @Column(name = "internName")
    private String internName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "userId")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BatchId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Batch batch;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trainingId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @OneToMany(mappedBy = "intern",fetch = FetchType.EAGER)
    private List<Attendance> attendanceList = new ArrayList<>();

    @OneToMany(mappedBy = "intern")
    @JsonIgnore
    private List<Scores> scoresList = new ArrayList<>();
}
