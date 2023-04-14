package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private BatchRepository batchRepository;
    @Override
    public Map<String, Object> createBatch(Integer trainingId, Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        Batch batch1 = new Batch();
        batch1.setBatchName(batch.getBatchName());
        batch1.setTraining(training);
        batchRepository.save(batch1);
        response.put("message","Batch created");
        response.put("batch",batch1);
        return response;
    }

    @Override
    public Map<String, Object> updateBatch(Integer batchId,Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Batch batch1 = batchRepository.findById(batchId).get();
        batch1.setBatchName(batch.getBatchName());
        batchRepository.save(batch1);
        response.put("message","Batch Updated");
        response.put("batch",batch1);
        return response;
    }

    @Override
    public Map<String, Object> getBatch(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        List<Batch> batchList = batchRepository.findByTraining(training);
        response.put("message","Batches Fetched");
        response.put("batch",batchList);
        return response;
    }

    @Override
    public Map<String, Object> getBatchById(Integer batchId) {
        Map<String,Object> response = new HashMap<>();
        Batch batch = batchRepository.findById(batchId).get();
        List<Intern> internList = batch.getInternList();
        response.put("message","Batch Fetched");
        response.put("internList",internList);
        response.put("batch",batch);
        return response;
    }
}
