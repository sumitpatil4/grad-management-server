package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public ResponseEntity<?> createTraining(String userId, Training training) {
        Map<String, Object> response = new HashMap<>();
        User user;
        Training newTraining;
        Batch batch;
        try{
            user = userRepository.findById(userId).get();
            newTraining = new Training();
            newTraining.setTrainingId(training.getTrainingId());
            newTraining.setTrainingName(training.getTrainingName());
            newTraining.setUser(user);
            trainingRepository.save(newTraining);
            batch = new Batch();
            batch.setBatchName(newTraining.getTrainingName()+"_"+Integer.toString(newTraining.getTrainingId()));
            batch.setTraining(newTraining);
            batch.setActive(true);
            batchRepository.save(batch);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Training created");
        response.put("training",newTraining);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTrainingsById(String userId) {
        Map<String, Object> response = new HashMap<>();
        User user;
        List<Training> trainingsList;
        try{
            user = userRepository.findById(userId).get();
            trainingsList = trainingRepository.findByUser(user);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Trainings Fetched");
        response.put("training",trainingsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTraining(Training training) {
        Map<String, Object> response = new HashMap<>();
        Training newTraining;
        Batch batch;
        try{
            newTraining = trainingRepository.findById(training.getTrainingId()).get();
            batch = batchRepository.findByBatchName(newTraining.getTrainingName()+"_"+Integer.toString(newTraining.getTrainingId())).get(0);
            newTraining.setTrainingName(training.getTrainingName());
            batch.setBatchName(newTraining.getTrainingName()+"_"+Integer.toString(newTraining.getTrainingId()));
            trainingRepository.save(newTraining);
            batchRepository.save(batch);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Training updated");
        response.put("training",newTraining);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTraining(Integer trainingId) {
        Map<String, Object> response = new HashMap<>();
        try{
            trainingRepository.deleteById(trainingId);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","Training Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
