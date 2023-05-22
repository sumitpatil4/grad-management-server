package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Training;
import com.example.gradmanagementserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Integer> {
    public List<Training> findByUser(User user);
}
