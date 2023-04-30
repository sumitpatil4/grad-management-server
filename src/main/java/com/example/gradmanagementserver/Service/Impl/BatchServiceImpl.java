package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.BatchRepository;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.MeetingRepository;
import com.example.gradmanagementserver.Repository.TrainingRepository;
import com.example.gradmanagementserver.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public ResponseEntity<?> createBatch(Integer trainingId, Batch batch) {
        Map<String,Object> response = new HashMap<>();
        Training training;
        List<Batch> batchList;
        List<Batch> filteredBatchList;
        Batch batch1;
        try{
            training = trainingRepository.findById(trainingId).get();
            batchList = training.getBatchList();
            filteredBatchList = batchList.stream().filter(batch2 -> batch2.isActive() && batch2.getBatchName().equalsIgnoreCase(batch.getBatchName())).collect(Collectors.toList());
            if(filteredBatchList.size()!=0){
                response.put("message","Batch already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else{
                batch1 = new Batch();
                batch1.setBatchName(batch.getBatchName());
                batch1.setTraining(training);
                batch1.setActive(true);
                batchRepository.save(batch1);
            }
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
    public ResponseEntity<?> getInternsByBatch(Map<Object,Object> batchList) {
        Map<String,Object> response = new HashMap<>();
        Batch batch;
        List<Integer> integerList = (List)batchList.get("batchList");
        List<Intern> internList=new ArrayList<>();
        try{
            for(Integer batchId : integerList){
                batch = batchRepository.findById(batchId).get();
                internList.addAll(batch.getInternList());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Interns Fetched");
        response.put("interns",internList);
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

    @Override
    public ResponseEntity<?> checkBatchAvailability(Integer batchId, MeetingDto meetingDto) {
        Map<String,Object> response = new HashMap<>();
        List<Meeting> meetingList = new ArrayList<>();
        Batch batch;
        Training training;
        Integer flag = 0;
        try{
            training = trainingRepository.findById(meetingDto.getTrainingId()).get();
            batch = batchRepository.findById(batchId).get();
            meetingList = batch.getMeetingList();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = df.format(meetingDto.getDate());
            meetingList = meetingList.stream().filter(meeting -> df.format(meeting.getDate()).equals(d1)).collect(Collectors.toList());
            for(Meeting m:meetingList){
                int start1 = meetingDto.getFromTime().compareTo(m.getFromTime());
                int start2 = meetingDto.getFromTime().compareTo(m.getToTime());
                if(start1>=0 && start2<0){
                    flag=1;
                    break;
                }
                int end1 = meetingDto.getToTime().compareTo(m.getFromTime());
                int end2 = meetingDto.getToTime().compareTo(m.getToTime());
                if(end1>0 && end2<=0){
                    flag=1;
                    break;
                }

                int over1 = meetingDto.getFromTime().compareTo(m.getFromTime());
                int over2 = meetingDto.getToTime().compareTo(m.getToTime());
                if(over1<=0 && over2>=0){
                    flag=1;
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Possibility Check");
        response.put("result",flag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
