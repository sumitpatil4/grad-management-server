package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.User;

import java.util.Map;

public interface UserService {
    public Map<String,Object> login(String token);
}
