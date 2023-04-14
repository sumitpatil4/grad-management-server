package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/createBatch/{trainingId}")
    public ResponseEntity<?> createBatch(@PathVariable Integer trainingId, @RequestBody Batch batch){
        Map<String,Object> response = batchService.createBatch(trainingId,batch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getBatch/{trainingId}")
    public ResponseEntity<?> getBatch(@PathVariable Integer trainingId){
        Map<String,Object> response = batchService.getBatch(trainingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getBatchById/{batchId}")
    public ResponseEntity<?> getBatchById(@PathVariable Integer batchId){
        Map<String,Object> response = batchService.getBatchById(batchId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateBatch/{batchId}")
    @CrossOrigin("**")
    public ResponseEntity<?> updateBatch(@PathVariable Integer batchId,@RequestBody Batch batch){
        Map<String,Object> response = batchService.updateBatch(batchId,batch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
