package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AvailabilityService {
    public ResponseEntity<?> createAvailability(Integer trainerId, Availability availability);
    public ResponseEntity<?> getAvailability(Integer trainerId);

    public ResponseEntity<?> deleteAvailability(Integer availabilityId);

    public ResponseEntity<?> updateAvailability(Integer availabilityId,Availability availability);
    public ResponseEntity<?> updateAvailabilityStatus(Integer availabilityId, Integer flag);
}
