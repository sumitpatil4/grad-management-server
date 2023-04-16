package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.*;
import com.example.gradmanagementserver.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private TopicRepository topicRepository;
    @Override
    public ResponseEntity<?> createMeeting(MeetingDto meetingDto) {
        Map<String,Object> response = new HashMap<>();
        Meeting meeting;
        Trainer trainer;
        Training training;
        Topic topic;
        List<Batch> batchList = new ArrayList<>();
        try{
            trainer =  trainerRepository.findById(meetingDto.getTrainerId()).get();
            training = trainingRepository.findById(meetingDto.getTrainingId()).get();
            topic = topicRepository.findById(meetingDto.getTopicId()).get();
            meeting =  new Meeting();
            meeting.setDate(meetingDto.getDate());
            meeting.setTime(meetingDto.getTime());
            meeting.setMeetingDesc(meetingDto.getMeetingDesc());
            meeting.setMeetingLink(meetingDto.getMeetingLink());
            meeting.setFeedbackLink(meetingDto.getFeedbackLink());
            meeting.setAssessmentLink(meetingDto.getAssessmentLink());
            for(Integer batchId:meetingDto.getBatchList()){
                batchList.add(batchRepository.findById(batchId).get());
            }
            meeting.setBatchList(batchList);
            meeting.setTrainer(trainer);
            meeting.setTraining(training);
            meeting.setTopic(topic);
            meetingRepository.save(meeting);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Meeting created");
        response.put("meeting",meeting);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMeetings(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        List<Meeting> meetingList;
        Training training;
        try {
            training = trainingRepository.findById(trainingId).get();
            meetingList = meetingRepository.findByTraining(training);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Meetings Fetched");
        response.put("meeting",meetingList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteMeeting(Integer meetingId) {
        Map<String,Object> response = new HashMap<>();
        Meeting meeting;
        try {
            meeting = meetingRepository.findById(meetingId).get();
            meetingRepository.delete(meeting);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Meeting deleted");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateMeeting(MeetingDto meetingDto) {
        Map<String,Object> response = new HashMap<>();
        Meeting meeting;
        try{
            meeting = meetingRepository.findById(meetingDto.getMeetingId()).get();
            meeting.setDate(meetingDto.getDate());
            meeting.setTime(meetingDto.getTime());
            meeting.setMeetingDesc(meetingDto.getMeetingDesc());
            meeting.setMeetingLink(meetingDto.getMeetingLink());
            meeting.setFeedbackLink(meetingDto.getFeedbackLink());
            meeting.setAssessmentLink(meetingDto.getAssessmentLink());
            meetingRepository.save(meeting);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Meeting updated");
        response.put("meeting",meeting);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}