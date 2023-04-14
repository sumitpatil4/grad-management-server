package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Training;

import java.util.Map;

public interface BatchService {
    public Map<String,Object> createBatch(Integer trainingId, Batch batch);

    public Map<String,Object> updateBatch(Integer batchId, Batch batch);

    public Map<String,Object> getBatch(Integer trainingId);

    public Map<String,Object> getBatchById(Integer batchId);
}
