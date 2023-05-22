package com.example.gradmanagementserver.Model;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InterListDto {
    private List<Integer> internIdList;
}
