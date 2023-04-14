package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Topic;

import java.util.Map;

public interface TopicService {
    public Map<String,Object> createTopic(Integer trainingId, Topic topic);

    public Map<String,Object> getTopics(Integer trainingId);
    public Map<String,Object> updateTopic(Integer topicId, Topic topic);

    public Map<String,Object> deleteTopic(Integer topicId);
    public Map<String,Object> updateCompleted(Integer topicId, Integer flag);


}
