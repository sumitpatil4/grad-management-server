package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.TrainerRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Override
    public Map<String, Object> createTrainer(String userId,Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        List<Trainer> trainerList = trainerRepository.findByEmail(trainer.getEmail());
        List<Trainer> filteredTrainer = trainerList.stream().filter(trainer1 -> trainer1.getUser().getUserId().equals(userId)).collect(Collectors.toList());
        Trainer newTrainer;
        if(filteredTrainer.size()!=0){
            newTrainer = filteredTrainer.get(0);
        }
        else{
            newTrainer = null;
        }
        if(newTrainer!=null && newTrainer.getUser().getUserId().equals(userId)){
            newTrainer.setActive(true);
            newTrainer.setTrainerName(trainer.getTrainerName());
            newTrainer.setSkill(trainer.getSkill());
            newTrainer.setPhoneNumber(trainer.getPhoneNumber());
            trainerRepository.save(newTrainer);
            response.put("message","Trainer set as active");
        }
        else {
            newTrainer = newTrainer = new Trainer(
                    trainer.getTrainerId(),
                    trainer.getTrainerName(),
                    trainer.getEmail(),
                    trainer.getPhoneNumber(),
                    trainer.getSkill(),
                    true
            );
            newTrainer.setUser(user);
            trainerRepository.save(newTrainer);
            response.put("message", "Trainer created");
        }
        response.put("trainer",newTrainer);
        return response;
    }

    @Override
    public Map<String, Object> getTrainerById(String userId) {
        Map<String,Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        List<Trainer> trainerList = trainerRepository.findByUser(user);
        System.out.println(trainerList.get(0).getTrainerName());
        List<Trainer> filteredList = trainerList.stream().filter(trainer -> trainer.isActive()).collect(Collectors.toList());
        response.put("message", "Trainers Fetched");
        response.put("trainers",filteredList);
        return response;
    }

    @Override
    public Map<String, Object> updateTrainer(Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer = trainerRepository.findById(trainer.getTrainerId()).get();
        newTrainer.setTrainerName(trainer.getTrainerName());
        newTrainer.setEmail(trainer.getEmail());
        newTrainer.setSkill(trainer.getSkill());
        newTrainer.setPhoneNumber(trainer.getPhoneNumber());
        trainerRepository.save(newTrainer);
        response.put("message","Updated Trainer");
        response.put("trainer",newTrainer);
        return response;
    }

    @Override
    public Map<String, Object> deleteTrainer(Integer trainerId) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer = trainerRepository.findById(trainerId).get();
        newTrainer.setActive(false);
        trainerRepository.save(newTrainer);
        response.put("message","Deleted Trainer");
        response.put("trainer",newTrainer);
        return response;
    }
}
