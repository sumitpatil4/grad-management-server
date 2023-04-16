package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/createTopic/{topicId}")
    public ResponseEntity<?> createTopic(@PathVariable Integer topicId, @RequestBody Topic topic){
        return topicService.createTopic(topicId,topic);
    }

    @GetMapping("getTopics/{trainingId}")
    public ResponseEntity<?> getTopics(@PathVariable Integer trainingId){
        return topicService.getTopics(trainingId);
    }

    @PutMapping("/updateTopic/{trainingId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTopic(@PathVariable Integer trainingId, @RequestBody Topic topic){
        return topicService.updateTopic(trainingId,topic);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId){
        return topicService.deleteTopic(topicId);
    }

    @PutMapping("/updateCompleted/{topicId}/{flag}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateCompleted(@PathVariable Integer topicId, @PathVariable Integer flag){
        return topicService.updateCompleted(topicId,flag);
    }
}
