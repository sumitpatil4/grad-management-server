package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/getTrainingById/{userId}")
    public ResponseEntity<?> getTrainingsById(@PathVariable String userId){
        return  trainingService.getTrainingsById(userId);
    }

    @PostMapping("/createTraining/{userId}")
    public ResponseEntity<?> createTraining(@PathVariable String userId, @RequestBody Training training){
        return trainingService.createTraining(userId, training);
    }

    @PutMapping("/updateTraining")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTraining(@RequestBody Training training){
        return trainingService.updateTraining(training);
    }

    @DeleteMapping("/deleteTraining/{trainingId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTraining(@PathVariable Integer trainingId){
        return trainingService.deleteTraining(trainingId);
    }
}
