package com.example.gradmanagementserver.Model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceDto {
    private List<Integer> idList;
    private List<Boolean> attendanceList;
}
