package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Integer> {
}
