package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Repository.AvailabilityRepository;
import com.example.gradmanagementserver.Repository.TrainerRepository;
import com.example.gradmanagementserver.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Map<String, Object> createAvailability(Integer trainerId, Availability availability) {
        Map<String,Object> response = new HashMap<>();
        Trainer trainer = trainerRepository.findById(trainerId).get();
        System.out.println(availability.getFromTime());
        System.out.println(availability.getToTime());
        Availability newAvailability = new Availability(
                availability.getAvailabilityId(),
                availability.getDate(),
                availability.getFromTime(),
                availability.getToTime(),
                trainer
        );
        availabilityRepository.save(newAvailability);
        response.put("message","Availability created");
        response.put("trainer",newAvailability);
        return response;
    }

    @Override
    public Map<String, Object> getAvailability(Integer trainerId) {
        Map<String,Object> response = new HashMap<>();
        Trainer trainer = trainerRepository.findById(trainerId).get();
        List<Availability> availabilityList = availabilityRepository.findByTrainer(trainer);
        response.put("message","Availability Fetched");
        response.put("availability",availabilityList);
        return response;
    }

    @Override
    public Map<String, Object> deleteAvailability(Integer availabilityId) {
        Map<String,Object> response = new HashMap<>();
        Availability availability = availabilityRepository.findById(availabilityId).get();
        availabilityRepository.delete(availability);
        response.put("message","Availability Deleted");
        response.put("availability",availability);
        return response;
    }
}
