package com.example.gradmanagementserver.Model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainerDto {
    private Integer topicId;
    private Date date;
}
