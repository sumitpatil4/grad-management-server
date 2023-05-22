package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Scores;
import com.example.gradmanagementserver.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Integer> {

    public List<Scores> findByTopic(Topic topic);

    public Scores findByTopicAndIntern(Topic topic, Intern intern);
}
