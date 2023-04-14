package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> getInternById(Integer internId) {
        Map<String,Object> response = new HashMap<>();
        Intern intern = internRepository.findById(internId).get();
        response.put("message","Intern Fetched");
        response.put("intern",intern);
        return response;
    }

    @Override
    public Map<String, Object> getInterns(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        List<Intern> internList = internRepository.findByTraining(training);
        response.put("message","Intern Fetched");
        response.put("intern",internList);
        return response;
    }

    @Override
    public Map<String, Object> createInterns(String userId, Integer trainingId, Intern intern) {
        Map<String,Object> response = new HashMap<>();
        User user = userRepository.findById(userId).get();
        Training training = trainingRepository.findById(trainingId).get();
        String defBatchName = training.getTrainingName()+"_"+Integer.toString(trainingId);
        Batch batch = batchRepository.findByBatchName(defBatchName).get(0);
        Intern newIntern = new Intern();
        newIntern.setInternId(intern.getInternId());
        newIntern.setInternName(intern.getInternName());
        newIntern.setEmail(intern.getEmail());
        newIntern.setPhoneNumber(intern.getPhoneNumber());
        newIntern.setBatch(batch);
        newIntern.setTraining(training);
        newIntern.setUser(user);
        internRepository.save(newIntern);
        response.put("message","Intern Created");
        response.put("intern",newIntern);
        return response;
    }

    @Override
    public Map<String, Object> updateIntern(Integer internId, Intern intern) {
        Map<String,Object> response = new HashMap<>();
        Intern newIntern = internRepository.findById(internId).get();
        newIntern.setInternName(intern.getInternName());
        newIntern.setEmail(intern.getEmail());
        newIntern.setPhoneNumber(intern.getPhoneNumber());
        internRepository.save(newIntern);
        response.put("message","Intern Updated");
        response.put("intern",newIntern);
        return response;
    }

    @Override
    public Map<String, Object> deleteIntern(Integer internId) {
        Map<String,Object> response = new HashMap<>();
        Intern intern = internRepository.findById(internId).get();
        internRepository.delete(intern);
        response.put("message","Intern Deleted");
        response.put("intern",intern);
        return response;
    }
}
