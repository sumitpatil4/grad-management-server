package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping("/createAvailability/{trainerId}")
    public ResponseEntity<?> createAvailability(@PathVariable Integer trainerId, @RequestBody Availability availability){
        return availabilityService.createAvailability(trainerId, availability);
    }

    @GetMapping("/getAvailability/{trainerId}")
    public ResponseEntity<?> getAvailability(@PathVariable Integer trainerId){
       return availabilityService.getAvailability(trainerId);
    }

    @DeleteMapping("/deleteAvailability/{availabilityId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteAvailability(@PathVariable Integer availabilityId){
        return availabilityService.deleteAvailability(availabilityId);
    }

    @PutMapping("/updateAvailability/{availabilityId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateAvailability(@PathVariable Integer availabilityId, @RequestBody Availability availability){
        return availabilityService.updateAvailability(availabilityId,availability);
    }
}
