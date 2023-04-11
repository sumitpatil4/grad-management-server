package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
}
