package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.ScoresDto;
import org.springframework.http.ResponseEntity;

public interface ScoresService {

    public ResponseEntity<?> createScores(Integer topicId, ScoresDto scoresDto);
    public ResponseEntity<?> getScores(Integer topicId);
}
