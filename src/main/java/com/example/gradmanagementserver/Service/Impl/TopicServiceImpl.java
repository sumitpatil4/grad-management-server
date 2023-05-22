package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public ResponseEntity<?> createTopic(Integer trainingId, Topic topic) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        Topic newTopic;
        List<Topic> topicList;
        List<Topic> filteredTopicList;
        try{
            training = trainingRepository.findById(trainingId).get();
            topicList = topicRepository.findByTraining(training);
            filteredTopicList = topicList.stream().filter(topic1 ->topic1.isActive() && topic1.getTopicName().equalsIgnoreCase(topic.getTopicName())).collect(Collectors.toList());
            if(filteredTopicList.size()!=0){
                response.put("message","Topic Already Exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {
                newTopic = new Topic();
                newTopic.setTopicName(topic.getTopicName());
                newTopic.setTraining(training);
                newTopic.setCompleted(false);
                newTopic.setActive(true);
                topicRepository.save(newTopic);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Topic created");
        response.put("topic",newTopic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTopics(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        List<Topic> topicList;
        List<Topic> filteredTopicList;
        try{
            training = trainingRepository.findById(trainingId).get();
            topicList = topicRepository.findByTraining(training);
            filteredTopicList = topicList.stream().filter(topic -> topic.isActive()).collect(Collectors.toList());
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Topics Fetched");
        response.put("topicList",filteredTopicList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTopic(Integer topicId, Topic topic) {
        Map<String,Object> response = new HashMap<>();
        Topic newTopic;
        try{
            newTopic = topicRepository.findById(topicId).get();
            newTopic.setTopicName(topic.getTopicName());
            topicRepository.save(newTopic);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Topic Updated");
        response.put("topicList",newTopic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTopic(Integer topicId) {
        Map<String,Object> response = new HashMap<>();
        Topic newTopic;
        try{
            newTopic = topicRepository.findById(topicId).get();
            if(newTopic.isCompleted()==true){
                newTopic.setActive(false);
                topicRepository.save(newTopic);
                response.put("message","Topic set as inactive");
            }
            else{
                topicRepository.delete(newTopic);
                response.put("message","Topic Deleted");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("topicList",newTopic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCompleted(Integer topicId, Integer flag) {
        Map<String,Object> response = new HashMap<>();
        Topic topic;
        try{
            topic = topicRepository.findById(topicId).get();
            if(flag==1){
                topic.setCompleted(true);
                response.put("message","Topic set as Complete");
            }
            else{
                topic.setCompleted(false);
                response.put("message","Topic set as Incomplete");
            }
            topicRepository.save(topic);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("topicList",topic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
