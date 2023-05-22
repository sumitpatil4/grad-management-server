package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.MeetingDto;
import com.example.gradmanagementserver.Model.Training;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BatchService {
    public ResponseEntity<?> createBatch(Integer trainingId, Batch batch);

    public ResponseEntity<?> updateBatch(Integer batchId, Batch batch);

    public ResponseEntity<?> getBatches(Integer trainingId);

    public ResponseEntity<?> deleteBatch(Integer batchId);
    public ResponseEntity<?> getInternsByBatch(Map<Object,Object> batchList);


    public ResponseEntity<?> getBatchById(Integer batchId);
    public ResponseEntity<?> updateInternBatch(Integer batchId, Integer defBatchId, InterListDto interListDto);

    public ResponseEntity<?> checkBatchAvailability(Integer batchId,MeetingDto meetingDto);
}
