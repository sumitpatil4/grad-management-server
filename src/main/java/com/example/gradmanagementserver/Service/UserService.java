package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public ResponseEntity<?> login(String token);

    public ResponseEntity<?> getUsers();

    public ResponseEntity<?> updateRole(String role,User user);

    public ResponseEntity<?> getUserById(String userId);
}
