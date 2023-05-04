package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.ScoresDto;
import com.example.gradmanagementserver.Service.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    @PostMapping("createScores/{topicId}")
    public ResponseEntity<?> createScores(@PathVariable Integer topicId, @RequestBody ScoresDto scoresDto){
        return scoresService.createScores(topicId, scoresDto);
    }

    @GetMapping("/getScores/{topicId}")
    public ResponseEntity<?> getScores(@PathVariable Integer topicId){
        return scoresService.getScores(topicId);
    }
}
