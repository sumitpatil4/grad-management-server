package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/intern")
public class InterController {

    @Autowired
    private InternService internService;

    @GetMapping("/getInternById/{internId}")
    public ResponseEntity<?> getInterNById(@PathVariable Integer internId){
        Map<String,Object> response = internService.getInternById(internId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getInterns/{trainingId}")
    public ResponseEntity<?> getInterns(@PathVariable Integer trainingId){
        Map<String,Object> response = internService.getInterns(trainingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createIntern/{userId}/{trainingId}")
    public ResponseEntity<?> createIntern(@PathVariable String userId,@PathVariable Integer trainingId,@RequestBody Intern intern){
        Map<String,Object> response = internService.createInterns(userId,trainingId,intern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateIntern/{internId}")
    @CrossOrigin("**")
    public ResponseEntity<?> updateIntern(@PathVariable Integer internId,@RequestBody Intern intern){
        Map<String,Object> response = internService.updateIntern(internId,intern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteIntern/{internId}")
    @CrossOrigin("**")
    public ResponseEntity<?> deleteIntern(@PathVariable Integer internId){
        Map<String,Object> response = internService.deleteIntern(internId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateInternBatch/{batchId}")
    @CrossOrigin("**")
    public ResponseEntity<?> updateInternBatch(@PathVariable Integer batchId, @RequestBody InterListDto interListDto) {
        Map<String, Object> response = internService.updateInternBatch(batchId, interListDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/deleteInternBatch/{internId}/{defBatchId}")
    @CrossOrigin("**")
    public ResponseEntity<?> deleteInternBatch(@PathVariable Integer internId, @PathVariable Integer defBatchId) {
        Map<String, Object> response = internService.deleteInternBatch(internId, defBatchId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
