package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Trainer;

import java.util.Map;

public interface TrainerService {
    public Map<String,Object> createTrainer(String userId, Trainer trainer);

    public Map<String,Object> getTrainerById(String userId);

    public Map<String,Object> updateTrainer(Trainer trainer);
    public Map<String,Object> deleteTrainer(Integer trainerId);
}
