package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.Training;

import java.util.Map;

public interface BatchService {
    public Map<String,Object> createBatch(Integer trainingId, Batch batch);

    public Map<String,Object> updateBatch(Integer batchId, Batch batch);

    public Map<String,Object> getBatches(Integer trainingId);

    public Map<String,Object> deleteBatch(Integer batchId);

    public Map<String,Object> getBatchById(Integer batchId);
    public Map<String,Object> updateInternBatch(Integer batchId, Integer defBatchId, InterListDto interListDto);

}
