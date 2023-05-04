package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.ScoresDto;
import com.example.gradmanagementserver.Service.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    @PostMapping("createScores/{topicId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createScores(@PathVariable Integer topicId, @RequestBody ScoresDto scoresDto){
        return scoresService.createScores(topicId, scoresDto);
    }
}
