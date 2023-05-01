package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Model.TrainerDto;
import com.example.gradmanagementserver.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/createTrainer/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createTrainer(@PathVariable String userId, @RequestBody Trainer trainer){
        return trainerService.createTrainer(userId,trainer);
    }

    @GetMapping("/getTrainersById/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getTrainersById(@PathVariable String userId){
        return trainerService.getTrainerById(userId);
    }

    @PostMapping("/getTrainersByAvlAndSkill/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getTrainersByAvlAndSkill(@PathVariable String userId, @RequestBody TrainerDto trainerDto){
        return trainerService.getTrainersByAvlAndSkill(userId,trainerDto);
    }

    @PostMapping("/sendMails")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> sendMails(@RequestParam("file") MultipartFile file, @RequestParam("str") String mailList){
        return trainerService.sendMails(file,mailList);
    }

    @PutMapping("/updateTrainer")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateTrainer(@RequestBody Trainer trainer){
        return trainerService.updateTrainer(trainer);
    }

    @DeleteMapping("/deleteTrainer/{trainerId}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteTrainer(@PathVariable Integer trainerId){
        return trainerService.deleteTrainer(trainerId);
    }
}
