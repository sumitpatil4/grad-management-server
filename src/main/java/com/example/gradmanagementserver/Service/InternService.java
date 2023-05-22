package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.Intern;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface InternService {

    public ResponseEntity<?> getInternById(Integer internId);
    public ResponseEntity<?> getInterns(Integer trainingId);

    public ResponseEntity<?> createInterns(String userId, Integer trainingId, Intern intern);

    public ResponseEntity<?> updateIntern(Integer internId,Intern intern);

    public ResponseEntity<?> deleteIntern(Integer internId);

    public ResponseEntity<?> updateInternBatch(Integer batchId, InterListDto interListDto);

    public ResponseEntity<?> deleteInternBatch(Integer internId, Integer defBatchId);

}
