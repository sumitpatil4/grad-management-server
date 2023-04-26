package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.InternService;
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
public class InternServiceImpl implements InternService {

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<?> getInternById(Integer internId) {
        Map<String,Object> response = new HashMap<>();
        Intern intern;
        try{
            intern = internRepository.findById(internId).get();
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Fetched");
        response.put("intern",intern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getInterns(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        List<Intern> internList;
        try{
            training = trainingRepository.findById(trainingId).get();
            internList = internRepository.findByTraining(training);
            for(Intern intern : internList){
                intern.setAttendanceList(new ArrayList<>());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Fetched");
        response.put("intern",internList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createInterns(String userId, Integer trainingId, Intern intern) {
        Map<String,Object> response = new HashMap<>();
        User user;
        Training training;
        Batch batch;
        Intern newIntern;
        List<Intern> internList;
        List<Intern> filteredInternList;
        try{
            user = userRepository.findById(userId).get();
            training = trainingRepository.findById(trainingId).get();
            internList = training.getInternList();
            filteredInternList = internList.stream().filter(intern1 -> intern1.getEmail().equalsIgnoreCase(intern.getEmail())).collect(Collectors.toList());
            if(filteredInternList.size()!=0){
                response.put("message","Intern already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else{
                String defBatchName = training.getTrainingName()+"_"+Integer.toString(trainingId);
                batch = batchRepository.findByBatchName(defBatchName).get(0);
                newIntern = new Intern();
                newIntern.setInternId(intern.getInternId());
                newIntern.setInternName(intern.getInternName());
                newIntern.setEmail(intern.getEmail());
                newIntern.setPhoneNumber(intern.getPhoneNumber());
                newIntern.setBatch(batch);
                newIntern.setTraining(training);
                newIntern.setUser(user);
                internRepository.save(newIntern);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Created");
        response.put("intern",newIntern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateIntern(Integer internId, Intern intern) {
        Map<String,Object> response = new HashMap<>();
        Intern newIntern;
        try{
            newIntern = internRepository.findById(internId).get();
            newIntern.setInternName(intern.getInternName());
            newIntern.setEmail(intern.getEmail());
            newIntern.setPhoneNumber(intern.getPhoneNumber());
            internRepository.save(newIntern);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Updated");
        response.put("intern",newIntern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteIntern(Integer internId) {
        Map<String,Object> response = new HashMap<>();
        Intern intern;
        try{
            intern = internRepository.findById(internId).get();
            internRepository.delete(intern);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Deleted");
        response.put("intern",intern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateInternBatch(Integer batchId, InterListDto interListDto) {
        Map<String,Object> response = new HashMap<>();
        Batch batch;
        try{
            batch = batchRepository.findById(batchId).get();
            for(Integer internId : interListDto.getInternIdList()){
                Intern intern =  internRepository.findById(internId).get();
                intern.setBatch(batch);
                internRepository.save(intern);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Batch Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteInternBatch(Integer internId, Integer defBatchId) {
        Map<String,Object> response = new HashMap<>();
        Batch batch;
        Intern intern;
        try{
            batch = batchRepository.findById(defBatchId).get();
            intern = internRepository.findById(internId).get();
            intern.setBatch(batch);
            internRepository.save(intern);
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Batch Updated to default");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
