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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createBatch(Integer trainingId, Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        Batch batch1;
        try{
            training = trainingRepository.findById(trainingId).get();
            batch1 = new Batch();
            batch1.setBatchName(batch.getBatchName());
            batch1.setTraining(training);
            batch1.setActive(true);
            batchRepository.save(batch1);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Batch created");
        response.put("batch",batch1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBatch(Integer batchId,Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Batch batch1;
        try{
            batch1 = batchRepository.findById(batchId).get();
            batch1.setBatchName(batch.getBatchName());
            batchRepository.save(batch1);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Batch Updated");
        response.put("batch",batch1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBatches(Integer trainingId) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        List<Batch> batchList;
        List<Batch> filteredBatchList;
        try{
            training = trainingRepository.findById(trainingId).get();
            batchList = batchRepository.findByTraining(training);
            filteredBatchList = batchList.stream().filter(batch -> batch.isActive()).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Batches Fetched");
        response.put("batch",filteredBatchList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBatch(Integer batchId) {
        Map<String,Object> response = new HashMap<>();
        Batch batch;
        try{
            batch = batchRepository.findById(batchId).get();
            batch.setActive(false);
            batchRepository.save(batch);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Batch Deleted");
        response.put("batch",batch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBatchById(Integer batchId) {
        Map<String,Object> response = new HashMap<>();
        Batch batch;
        List<Intern> internList;
        try{
            batch = batchRepository.findById(batchId).get();
            internList = batch.getInternList();
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Batch Fetched");
        response.put("internList",internList);
        response.put("batch",batch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateInternBatch(Integer batchId, Integer defBatchId, InterListDto interListDto) {
        Map<String,Object> response = new HashMap<>();
        Batch deletedBatch;
        Batch defaultBatch;
        try{
            deletedBatch = batchRepository.findById(batchId).get();
            defaultBatch = batchRepository.findById(defBatchId).get();
            for(Integer internId : interListDto.getInternIdList()){
                Intern intern =  internRepository.findById(internId).get();
                intern.setBatch(defaultBatch);
                internRepository.save(intern);
            }
            deletedBatch.setActive(false);
            batchRepository.save(deletedBatch);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Intern Batch Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
