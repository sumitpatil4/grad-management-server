package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Jwt.JwtTokenUtil;
import com.example.gradmanagementserver.Model.Intern;
import com.example.gradmanagementserver.Model.Meeting;
import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static String ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<?> login(String token) {
        String userId;
        String uName;
        String email;
        String picture;
        List<Intern> internList;
        Map<String,Object> response = new HashMap<>();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        Map<String,String> userInfo = new HashMap<>();
        try{
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper mapper = new ObjectMapper();
            userInfo = mapper.readValue(payload, new TypeReference<Map<String, String>>() {});
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message","Invalid Token");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        userId = userInfo.get("sub");
        uName = userInfo.get("name");
        email = userInfo.get("email");
        picture = userInfo.get("picture");
        if(userId == null || uName == null || email == null || picture == null){
            response.put("message","Invalid Token");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        internList = internRepository.findByEmail(email);
        if(internList.size()!=0){
            User intern = new User();
            intern.setRole("ROLE_INTERN");
            intern.setEmail(email);
            intern.setPicture(picture);
            intern.setUName(uName);
            intern.setUserId(String.valueOf(internList.get(0).getInternId()));
            response.put("message","Intern Login!");
            response.put("user",intern);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.equals(Optional.empty())){
            User newUser = new User(
                    userId,
                    uName,
                    email,
                    picture,
                    ROLE
                    );
            userRepository.save(newUser);
            response.put("message ","Login Successful");
            response.put("user",newUser);
        }
        else{
            response.put("message ","Login Successful");
            response.put("user",user.get());
        }
        User newUser = (User) response.get("user");
        String accessToken = jwtTokenUtil.generateAccessToken(newUser);
        response.put("accessToken",accessToken);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> userList;
        try{
            userList= userRepository.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Users Fetched");
        response.put("userList",userList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateRole(String role, User user) {
        Map<String, Object> response = new HashMap<>();
        User newUser;
        try {
            newUser = userRepository.findById(user.getUserId()).get();
            newUser.setRole(role);
            userRepository.save(newUser);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("message","Role Updated");
        response.put("user",newUser);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(String userId) {
        Map<String, Object> response = new HashMap<>();
        User user;
        try{
            user= userRepository.findById(userId).get();
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","User Fetched");
        response.put("user",user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
