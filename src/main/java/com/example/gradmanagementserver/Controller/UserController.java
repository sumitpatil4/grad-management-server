package com.example.gradmanagementserver.Controller;

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
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;
    @GetMapping("/helloUser")
    public String hello(){
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("Authorization") String token){
        Map<String,Object> response = userService.login(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Object> getUsers(){
        Map<String,Object> response = userService.getUsers();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    @PutMapping("/updateRole/{role}")
//    public ResponseEntity<Object> updateRole(@PathVariable String role,@RequestBody User user){
//        user.setRole(role);
//        userRepository.save(user);
//        Map<String,Object> response = userService.updateRole(role,user);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
}
