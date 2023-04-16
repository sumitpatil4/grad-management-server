package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Trainer;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TrainerService {
    public ResponseEntity<?> createTrainer(String userId, Trainer trainer);

    public ResponseEntity<?> getTrainerById(String userId);

    public ResponseEntity<?> updateTrainer(Trainer trainer);
    public ResponseEntity<?> deleteTrainer(Integer trainerId);
}
