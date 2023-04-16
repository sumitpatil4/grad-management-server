package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Training;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TrainingService {

    public ResponseEntity<?> createTraining(String userId, Training training);

    public ResponseEntity<?> getTrainingsById(String userId);

    public ResponseEntity<?> updateTraining(Training training);

    public ResponseEntity<?> deleteTraining(Integer trainingId);
}
