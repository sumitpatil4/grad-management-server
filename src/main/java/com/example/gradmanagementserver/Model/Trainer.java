package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Availability> availabilityList=new ArrayList<>();

    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meeting> meetingList=new ArrayList<>();

    public Trainer(int trainerId, String trainerName, String email, String phoneNumber, String skill,boolean isActive) {
        this.trainerId=trainerId;
        this.trainerName=trainerName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.skill=skill;
        this.isActive=isActive;
    }

    public Trainer(Trainer t) {
        this.trainerId=t.getTrainerId();
        this.trainerName=t.getTrainerName();
        this.email=t.getEmail();
        this.phoneNumber=t.getPhoneNumber();
        this.skill=t.getSkill();
        this.isActive=t.isActive();
        this.user=t.getUser();
        this.meetingList=t.getMeetingList();
        this.availabilityList=t.getAvailabilityList();
    }
}
