package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern,Integer> {

    public List<Intern> findByTraining(Training training);

    public List<Intern> findByEmail(String email);
}
