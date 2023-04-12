package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Batch")
public class Batch{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "batchId")
    private int batchId;
    @Column(name = "batchName")
    private String batchName;

    @OneToMany(mappedBy = "batch",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Intern> internList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trainingId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "batch_meeting",
            joinColumns = @JoinColumn(name = "batchId"),
            inverseJoinColumns = @JoinColumn(name = "meetingId"))
    private List<Meeting> meetingList ;
}
