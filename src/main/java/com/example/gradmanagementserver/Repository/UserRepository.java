package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "update ",nativeQuery = true)
    public List<User> findByRole(String role);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.role = ?1 where u.userId = ?2")
    public void updateRole(String role,String userId);
}
