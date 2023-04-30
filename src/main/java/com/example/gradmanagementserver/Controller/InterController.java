package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.InterListDto;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/intern")
public class InterController {

    @Autowired
    private InternService internService;

    @GetMapping("/getInternById/{internId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getInternById(@PathVariable Integer internId){
        return internService.getInternById(internId);
    }

    @GetMapping("/getInterns/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getInterns(@PathVariable Integer trainingId){
        return internService.getInterns(trainingId);
    }

    @PostMapping("/createIntern/{userId}/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createIntern(@PathVariable String userId,@PathVariable Integer trainingId,@RequestBody Intern intern){
        return internService.createInterns(userId,trainingId,intern);
    }

    @PutMapping("/updateIntern/{internId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateIntern(@PathVariable Integer internId,@RequestBody Intern intern){
        return internService.updateIntern(internId,intern);
    }

    @DeleteMapping("/deleteIntern/{internId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteIntern(@PathVariable Integer internId){
        return internService.deleteIntern(internId);
    }

    @PutMapping("/updateInternBatch/{batchId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateInternBatch(@PathVariable Integer batchId, @RequestBody InterListDto interListDto) {
        return internService.updateInternBatch(batchId, interListDto);
    }

    @PutMapping("/deleteInternBatch/{internId}/{defBatchId}")
    @CrossOrigin("**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteInternBatch(@PathVariable Integer internId, @PathVariable Integer defBatchId) {
        return internService.deleteInternBatch(internId, defBatchId);
    }
}
