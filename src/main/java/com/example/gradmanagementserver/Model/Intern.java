package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private int interId;
    @Column(name = "internName")
    private String internName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "BatchId")
    @JsonIgnore
    private Batch batch;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trainingId")
    private Training training;
}
