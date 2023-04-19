package com.example.gradmanagementserver.Model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainerDto {
    private Integer topicId;
    private List<Date> dateList;
}
