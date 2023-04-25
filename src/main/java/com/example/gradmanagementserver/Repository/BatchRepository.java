package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Integer> {

    public List<Batch> findByBatchName(String name);
    public List<Batch> findByTraining(Training training);

}
