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
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "attendanceId")
    private int attendanceId;

    @Column(name = "isPresent")
    private boolean isPresent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Meeting meeting;

    @ManyToOne()
    @JoinColumn(name = "internId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Intern intern;
}
