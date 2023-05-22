package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Exception.InvalidJwtException;
import com.example.gradmanagementserver.Jwt.JwtTokenUtil;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String token){
        return userService.login(token);
    }
    @GetMapping("/getUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_LEADER')")
    public ResponseEntity<?> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("getUserById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/updateRole/{role}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable String role,@RequestBody User user){
        return userService.updateRole(role,user);
    }
}
