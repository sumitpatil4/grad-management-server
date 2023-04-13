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
    public ResponseEntity<Object> createAvailability(@PathVariable Integer trainerId, @RequestBody Availability availability){
        Map<String,Object> response = availabilityService.createAvailability(trainerId, availability);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAvailability/{trainerId}")
    public ResponseEntity<Object> getAvailability(@PathVariable Integer trainerId){
        Map<String,Object> response = availabilityService.getAvailability(trainerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAvailability/{availabilityId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<Object> deleteAvailability(@PathVariable Integer availabilityId){
        Map<String,Object> response = availabilityService.deleteAvailability(availabilityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
