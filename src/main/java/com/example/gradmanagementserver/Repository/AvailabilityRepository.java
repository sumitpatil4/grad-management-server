package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Availability;
import com.example.gradmanagementserver.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Integer> {

    public List<Availability> findByTrainer(Trainer trainer);

    @Transactional
    @Modifying
    @Query("delete from Availability a where a.availabilityId =:availabilityId")
    public void deleteUsingId(@Param("availabilityId") Integer availabilityId);
}
