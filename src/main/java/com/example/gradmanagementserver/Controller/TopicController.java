package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/createTopic/{topicId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createTopic(@PathVariable Integer topicId, @RequestBody Topic topic){
        return topicService.createTopic(topicId,topic);
    }

    @GetMapping("getTopics/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_LEADER')")
    public ResponseEntity<?> getTopics(@PathVariable Integer trainingId){
        return topicService.getTopics(trainingId);
    }

    @PutMapping("/updateTopic/{trainingId}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateTopic(@PathVariable Integer trainingId, @RequestBody Topic topic){
        return topicService.updateTopic(trainingId,topic);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId){
        return topicService.deleteTopic(topicId);
    }

    @PutMapping("/updateCompleted/{topicId}/{flag}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateCompleted(@PathVariable Integer topicId, @PathVariable Integer flag){
        return topicService.updateCompleted(topicId,flag);
    }
}
