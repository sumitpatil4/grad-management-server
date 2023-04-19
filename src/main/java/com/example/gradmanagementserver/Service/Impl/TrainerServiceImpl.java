package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Repository.TrainerRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TopicRepository topicRepository;
    @Override
    public ResponseEntity<?> createTrainer(String userId, Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        User user;
        List<Trainer> trainerList;
        List<Trainer> filteredTrainer;
        Trainer newTrainer;
        try{
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByEmail(trainer.getEmail());
            filteredTrainer = trainerList.stream().filter(trainer1 -> trainer1.getUser().getUserId().equals(userId)).collect(Collectors.toList());
            if(filteredTrainer.size()!=0){
                newTrainer = filteredTrainer.get(0);
            }
            else{
                newTrainer = null;
            }
            if(newTrainer!=null){
                newTrainer.setActive(true);
                newTrainer.setTrainerName(trainer.getTrainerName());
                newTrainer.setSkill(trainer.getSkill());
                newTrainer.setPhoneNumber(trainer.getPhoneNumber());
                trainerRepository.save(newTrainer);
                response.put("message","Trainer set as active");
            }
            else {
                newTrainer = newTrainer = new Trainer();
                newTrainer.setActive(true);
                newTrainer.setTrainerName(trainer.getTrainerName());
                newTrainer.setSkill(trainer.getSkill());
                newTrainer.setEmail(trainer.getEmail());
                newTrainer.setPhoneNumber(trainer.getPhoneNumber());
                newTrainer.setUser(user);
                trainerRepository.save(newTrainer);
                response.put("message", "Trainer created");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTrainerById(String userId) {
        Map<String,Object> response = new HashMap<>();
        User user;
        List<Trainer> trainerList;
        List<Trainer> filteredList;
        try{
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByUser(user);
            filteredList = trainerList.stream().filter(trainer -> trainer.isActive()).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Trainers Fetched");
        response.put("trainers",filteredList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTrainer(Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer;
        try{
            newTrainer = trainerRepository.findById(trainer.getTrainerId()).get();
            newTrainer.setTrainerName(trainer.getTrainerName());
            newTrainer.setEmail(trainer.getEmail());
            newTrainer.setSkill(trainer.getSkill());
            newTrainer.setPhoneNumber(trainer.getPhoneNumber());
            trainerRepository.save(newTrainer);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Updated Trainer");
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteTrainer(Integer trainerId) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer;
        try{
            newTrainer = trainerRepository.findById(trainerId).get();
            newTrainer.setActive(false);
            trainerRepository.save(newTrainer);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Deleted Trainer");
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTrainersByAvlAndSkill(String userId, TrainerDto trainerDto) {
        Map<String, Object> response = new HashMap<>();
        User user;
        String topicName;
        Topic topic;
        List<Trainer> trainerList;
        List<Trainer> filteredList;
        List<Trainer> finalList = new ArrayList<>();
        try {
            topic = topicRepository.findById(trainerDto.getTopicId()).get();
            topicName = topic.getTopicName();
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByUser(user);
            filteredList = trainerList.stream().filter(trainer -> trainer.getSkill().equals(topicName)).collect(Collectors.toList());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = df.format(trainerDto.getDate());
            for (Trainer t : filteredList) {
                List<Availability> availabilityList = t.getAvailabilityList();
                for (Availability a : availabilityList) {
                    String d2 = df.format(a.getDate());
                    if (d1.equals(d2)) {
                        finalList.add(t);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Trainers Fetched");
        response.put("trainers", finalList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
