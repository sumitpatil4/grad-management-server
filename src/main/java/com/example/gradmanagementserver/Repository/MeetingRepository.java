package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Batch;
import com.example.gradmanagementserver.Model.Meeting;
import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
    public List<Meeting> findByTraining(Training training);

}
