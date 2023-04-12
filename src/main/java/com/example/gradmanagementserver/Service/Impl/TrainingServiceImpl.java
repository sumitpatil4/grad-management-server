package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> createTraining(String userId, Training training) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        Training newTraining = new Training();
        newTraining.setTrainingId(training.getTrainingId());
        newTraining.setTrainingName(training.getTrainingName());
        newTraining.setUser(user);
        trainingRepository.save(newTraining);
        response.put("message","Training created");
        response.put("training",newTraining);
        return response;
    }

    @Override
    public Map<String, Object> getTrainingsById(String userId) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        List<Training> trainingsList = trainingRepository.findByUser(user);
        response.put("message","Trainings Fetched");
        response.put("training",trainingsList);
        return response;
    }

    @Override
    public Map<String, Object> updateTraining(Training training) {
        Map<String, Object> response = new HashMap<>();
        Training newTraining  = trainingRepository.findById(training.getTrainingId()).get();
        newTraining.setTrainingName(training.getTrainingName());
        trainingRepository.save(newTraining);
        response.put("message","Training updated");
        response.put("training",newTraining);
        return response;
    }

    @Override
    public Map<String, Object> deleteTraining(Integer trainingId) {
        Map<String, Object> response = new HashMap<>();
        trainingRepository.deleteUsingId(trainingId);
        response.put("message","Training Deleted");
        return response;
    }
}
