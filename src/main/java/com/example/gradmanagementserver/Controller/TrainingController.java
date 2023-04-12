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

    @GetMapping("/getTrainings")
    public ResponseEntity<?> getTrainings(){
        Map<String,Object> response = trainingService.getTraining();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/createTraining/{userId}")
    public ResponseEntity<?> createTraining(@PathVariable String userId, @RequestBody Training training){
        Map<String,Object> response = trainingService.createTraining(userId, training);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/updateTraining")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTraining(@RequestBody Training training){
        Map<String,Object> response = trainingService.updateTraining(training);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTraining/{trainingId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTraining(@PathVariable Integer trainingId){
        Map<String,Object> response = trainingService.deleteTraining(trainingId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
