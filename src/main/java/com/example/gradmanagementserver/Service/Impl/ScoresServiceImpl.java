package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Scores;
import com.example.gradmanagementserver.Model.ScoresDto;
import com.example.gradmanagementserver.Model.Topic;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.ScoresRepository;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Service.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return null;
    }
}
