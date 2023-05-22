package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.ScoresRepository;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Service.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoresServiceImpl implements ScoresService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public ResponseEntity<?> createScores(Integer topicId, ScoresDto scoresDto) {
        Map<String, Object> response = new HashMap<>();
        Topic topic;
        Intern intern;
        List<Scores> scoresList;
        Integer totalScore;
        Scores scor;
        try{
            topic = topicRepository.findById(topicId).get();
            scoresList = scoresRepository.findByTopic(topic);
            totalScore = scoresDto.getTotalScore();
            for(int i=0; i<scoresDto.getIdList().size();i++) {
                intern = internRepository.findById(scoresDto.getIdList().get(i)).get();

                int scr = scoresDto.getScoresList().get(i);
                if(scoresRepository.findByTopicAndIntern(topic, intern) == null) {
                    Scores scores = new Scores();
                    scores.setIntern(intern);
                    scores.setTopic(topic);
                    scores.setScore(scr);
                    scores.setTotalScore(totalScore);
                    scoresRepository.save(scores);
                }
                else {
                    Scores scores = scoresRepository.findByTopicAndIntern(topic, intern);
                    scores.setScore(scr);
                    scoresRepository.save(scores);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Scores Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getScores(Integer topicId) {
        Map<String,Object> response = new HashMap<>();
        Topic topic;
        List<Scores> scoresList;
        try{
            topic = topicRepository.findById(topicId).get();
            scoresList = scoresRepository.findByTopic(topic);
            for(Scores s : scoresList){
                s.getIntern().setAttendanceList(new ArrayList<>());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Scores Fetched");
        response.put("batch",scoresList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
