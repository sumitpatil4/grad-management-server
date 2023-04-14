package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public Map<String, Object> createBatch(Integer trainingId, Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        Batch batch1 = new Batch();
        batch1.setBatchName(batch.getBatchName());
        batch1.setTraining(training);
        batch1.setActive(true);
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
    public Map<String, Object> getBatches(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training = trainingRepository.findById(trainingId).get();
        List<Batch> batchList = batchRepository.findByTraining(training);
        List<Batch> newBatchList = batchList.stream().filter(batch -> batch.isActive()).collect(Collectors.toList());
        response.put("message","Batches Fetched");
        response.put("batch",newBatchList);
        return response;
    }

    @Override
    public Map<String, Object> deleteBatch(Integer batchId) {
        Map<String,Object> response = new HashMap<>();
        Batch batch = batchRepository.findById(batchId).get();
        batch.setActive(false);
        batchRepository.save(batch);
        response.put("message","Batch Deleted");
        response.put("batch",batch);
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

    @Override
    public Map<String, Object> updateInternBatch(Integer batchId, Integer defBatchId, InterListDto interListDto) {
        Map<String,Object> response = new HashMap<>();
        Batch deletedBatch = batchRepository.findById(batchId).get();
        Batch defaultBatch = batchRepository.findById(defBatchId).get();
        for(Integer internId : interListDto.getInternIdList()){
            Intern intern =  internRepository.findById(internId).get();
            intern.setBatch(defaultBatch);
            internRepository.save(intern);
        }
        deletedBatch.setActive(false);
        batchRepository.save(deletedBatch);
        response.put("message","Intern Batch Deleted");
        return response;
    }
}
