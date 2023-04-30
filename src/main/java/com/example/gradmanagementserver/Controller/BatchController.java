package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.MeetingDto;
import com.example.gradmanagementserver.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/createBatch/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createBatch(@PathVariable Integer trainingId, @RequestBody Batch batch){
        return batchService.createBatch(trainingId,batch);
    }

    @GetMapping("/getBatch/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getBatches(@PathVariable Integer trainingId){
        return batchService.getBatches(trainingId);
    }

    @GetMapping("/getBatchById/{batchId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getBatchById(@PathVariable Integer batchId){
        return batchService.getBatchById(batchId);
    }

    @PutMapping("/updateBatch/{batchId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateBatch(@PathVariable Integer batchId,@RequestBody Batch batch){
        return batchService.updateBatch(batchId,batch);
    }

    @DeleteMapping("deleteBatch/{batchId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteBatch(@PathVariable Integer batchId){
        return batchService.deleteBatch(batchId);
    }

    @PutMapping("/updateInternBatch/{batchId}/{defBatchId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateInternBatch(@PathVariable Integer batchId,@PathVariable Integer defBatchId, @RequestBody InterListDto interListDto) {
        return batchService.updateInternBatch(batchId, defBatchId, interListDto);
    }

    @PostMapping("/checkBatchAvailability/{batchId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> checkBatchAvailability(@PathVariable Integer batchId, @RequestBody MeetingDto meetingDto) {
        return batchService.checkBatchAvailability(batchId,meetingDto);
    }

    @PostMapping("/getInternsByBatch")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_LEADER')")
    public ResponseEntity<?> getInternsByBatch(@RequestBody Map<Object,Object> batchList) {
        return batchService.getInternsByBatch(batchList);
    }
}