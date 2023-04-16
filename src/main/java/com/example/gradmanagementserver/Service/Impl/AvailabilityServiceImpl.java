package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Repository.AvailabilityRepository;
import com.example.gradmanagementserver.Repository.TrainerRepository;
import com.example.gradmanagementserver.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createAvailability(Integer trainerId, Availability availability) {
        Map<String,Object> response = new HashMap<>();
        Trainer trainer;
        Availability newAvailability;
        try{
            trainer = trainerRepository.findById(trainerId).get();
            newAvailability = new Availability();
            newAvailability.setAvailabilityId((availability.getAvailabilityId()));
            newAvailability.setDate(availability.getDate());
            newAvailability.setFromTime(availability.getFromTime());
            newAvailability.setToTime(availability.getToTime());
            newAvailability.setTrainer(trainer);
            availabilityRepository.save(newAvailability);
            response.put("message","Availability created");
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("trainer",newAvailability);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAvailability(Integer trainerId) {
        Map<String,Object> response = new HashMap<>();
        Trainer trainer;
        List<Availability> availabilityList;
        try{
            trainer = trainerRepository.findById(trainerId).get();
            availabilityList = availabilityRepository.findByTrainer(trainer);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Availability Fetched");
        response.put("availability",availabilityList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteAvailability(Integer availabilityId) {
        Map<String,Object> response = new HashMap<>();
        Availability availability;
        try{
            availability = availabilityRepository.findById(availabilityId).get();
            availabilityRepository.delete(availability);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Availability Deleted");
        response.put("availability",availability);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateAvailability(Integer availabilityId, Availability availability) {
        Map<String,Object> response = new HashMap<>();
        Availability newAvailability;
        try{
            newAvailability = availabilityRepository.findById(availabilityId).get();
            newAvailability.setDate(availability.getDate());
            newAvailability.setFromTime(availability.getFromTime());
            newAvailability.setToTime(availability.getToTime());
            availabilityRepository.save(newAvailability);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Availability Updated");
        response.put("availability",availability);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
