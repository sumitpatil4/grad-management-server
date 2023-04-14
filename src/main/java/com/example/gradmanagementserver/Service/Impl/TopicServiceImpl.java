package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Map<String, Object> createTopic(Integer trainingId, Topic topic) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        Topic newTopic = new Topic();
        newTopic.setTopicName(topic.getTopicName());
        newTopic.setTraining(training);
        newTopic.setCompleted(false);
        newTopic.setActive(true);
        topicRepository.save(newTopic);
        response.put("message","Topic created");
        response.put("topic",newTopic);
        return response;
    }

    @Override
    public Map<String, Object> getTopics(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        List<Topic> topicList = topicRepository.findByTraining(training);
        response.put("message","Topics Fetched");
        response.put("topicList",topicList);
        return response;
    }

    @Override
    public Map<String, Object> updateTopic(Integer topicId, Topic topic) {
        Map<String,Object> response = new HashMap<>();
        Topic newTopic = topicRepository.findById(topicId).get();
        newTopic.setTopicName(topic.getTopicName());
        topicRepository.save(newTopic);
        response.put("message","Topic Updated");
        response.put("topicList",newTopic);
        return response;
    }

    @Override
    public Map<String, Object> deleteTopic(Integer topicId) {
        Map<String,Object> response = new HashMap<>();
        Topic newTopic = topicRepository.findById(topicId).get();
        if(newTopic.isCompleted()==true){
            newTopic.setActive(false);
            topicRepository.save(newTopic);
            response.put("message","Topic set as inactive");
        }
        else{
            topicRepository.delete(newTopic);
            response.put("message","Topic Deleted");
        }

        response.put("topicList",newTopic);
        return response;
    }

    @Override
    public Map<String, Object> updateCompleted(Integer topicId, Integer flag) {
        Map<String,Object> response = new HashMap<>();
        Topic topic = topicRepository.findById(topicId).get();
        if(flag==1){
            topic.setCompleted(true);
        }
        else{
            topic.setCompleted(false);
        }
        topicRepository.save(topic);
        response.put("message","Topic Updated");
        response.put("topicList",topic);
        return response;
    }
}
