package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

    public List<Topic> findByTraining(Training training);

    public List<Topic> findByTopicName(String name);
}
