package com.example.gradmanagementserver.Controller;

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
    public ResponseEntity<?> updateIntern(@PathVariable Integer internId,@RequestBody Intern intern){
        Map<String,Object> response = internService.updateIntern(internId,intern);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteIntern/{internId}")
    public ResponseEntity<?> deleteIntern(@PathVariable Integer internId){
        Map<String,Object> response = internService.deleteIntern(internId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
