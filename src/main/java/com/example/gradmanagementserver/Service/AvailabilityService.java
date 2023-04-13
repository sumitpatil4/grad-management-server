package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;

import java.util.Map;

public interface AvailabilityService {
    public Map<String,Object> createAvailability(Integer trainerId, Availability availability);
    public Map<String,Object> getAvailability(Integer trainerId);

    public Map<String,Object> deleteAvailability(Integer availabilityId);

}
