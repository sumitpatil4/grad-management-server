package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Training;

import java.util.List;
import java.util.Map;

public interface TrainingService {

    public Map<String,Object> createTraining(String userId,Training training);

    public Map<String,Object> getTrainingsById(String userId);

    public Map<String,Object> updateTraining(Training training);

    public Map<String,Object> deleteTraining(Integer trainingId);
}
