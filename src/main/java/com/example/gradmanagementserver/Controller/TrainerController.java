package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/createTrainer/{userId}")
    public ResponseEntity<?> createTrainer(@PathVariable String userId, @RequestBody Trainer trainer){
        Map<String,Object> response = trainerService.createTrainer(userId,trainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTrainersById/{userId}")
    public ResponseEntity<?> getTrainersById(@PathVariable String userId){
        Map<String,Object> response = trainerService.getTrainerById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateTrainer")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTrainer(@RequestBody Trainer trainer){
        Map<String,Object> response = trainerService.updateTrainer(trainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTrainer/{trainerId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTrainer(@PathVariable Integer trainerId){
        Map<String,Object> response = trainerService.deleteTrainer(trainerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
