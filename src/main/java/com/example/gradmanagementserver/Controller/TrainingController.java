package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/getTrainings")
    public ResponseEntity<Object> getTrainings(){
        List<Training> trainingList = trainingService.getTrainings();
        return new ResponseEntity<>(trainingList, HttpStatus.OK);
    }
}
