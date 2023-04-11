package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Override
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }
}
