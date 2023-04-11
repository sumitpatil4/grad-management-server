package com.example.gradmanagementserver.Model;

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
@Table(name = "Trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "trainerId")
    private int trainerId;
    @Column(name = "trainerName")
    private String trainerName;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "skill")
    private String skill;

    @ManyToMany(mappedBy = "trainerList",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> userList;

    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Availability> availabilityList;

    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meeting> meetingList;
}
