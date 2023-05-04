package com.example.gradmanagementserver.Model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScoresDto {
    private List<Integer> idList;
    private List<Integer> scoresList;
    private Integer totalScore;
}
