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
        Map<String,Object> response = topicService.createTopic(topicId,topic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getTopics/{trainingId}")
    public ResponseEntity<?> getTopics(@PathVariable Integer trainingId){
        Map<String,Object> response = topicService.getTopics(trainingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateTopic/{trainingId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateTopic(@PathVariable Integer trainingId, @RequestBody Topic topic){
        Map<String,Object> response = topicService.updateTopic(trainingId,topic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId){
        Map<String,Object> response = topicService.deleteTopic(topicId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateCompleted/{topicId}/{flag}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateCompleted(@PathVariable Integer topicId, @PathVariable Integer flag){
        Map<String,Object> response = topicService.updateCompleted(topicId,flag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
