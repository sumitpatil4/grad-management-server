package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Topic;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TopicService {
    public ResponseEntity<?> createTopic(Integer trainingId, Topic topic);

    public ResponseEntity<?> getTopics(Integer trainingId);
    public ResponseEntity<?> updateTopic(Integer topicId, Topic topic);

    public ResponseEntity<?> deleteTopic(Integer topicId);
    public ResponseEntity<?> updateCompleted(Integer topicId, Integer flag);


}
