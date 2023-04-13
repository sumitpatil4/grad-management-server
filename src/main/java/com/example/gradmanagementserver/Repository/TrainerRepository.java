package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Trainer;
import com.example.gradmanagementserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Integer> {

    public List<Trainer> findByEmail(String email);

    public List<Trainer> findByUser(User user);
}
