package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrainingRepository extends JpaRepository<Training,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Training t where t.trainingId =:trainingId")
    public void deleteUsingId(@Param("trainingId") Integer trainingId);
}
