package com.example.gradmanagementserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MeetingDto {
    private int meetingId;
    private String meetingDesc;
    private Date date;
    private Time fromTime;
    private Time toTime;
    private String eventId;
    private String meetingLink;
    private String feedbackLink;
    private String assessmentLink;
    private Integer topicId;
    private Integer trainingId;
    private Integer trainerId;
    private List<Integer> batchList;
    private Integer availabilityUsed;
}
