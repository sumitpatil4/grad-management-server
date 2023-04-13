package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Integer> {

    public List<Availability> findByTrainer(Trainer trainer);
}
