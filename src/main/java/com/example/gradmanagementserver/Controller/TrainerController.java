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
        return trainerService.createTrainer(userId,trainer);
    }

    @GetMapping("/getTrainersById/{userId}")
    public ResponseEntity<?> getTrainersById(@PathVariable String userId){
        return trainerService.getTrainerById(userId);
    }

    @PutMapping("/updateTrainer")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTrainer(@RequestBody Trainer trainer){
        return trainerService.updateTrainer(trainer);
    }

    @DeleteMapping("/deleteTrainer/{trainerId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTrainer(@PathVariable Integer trainerId){
        return trainerService.deleteTrainer(trainerId);
    }
}
