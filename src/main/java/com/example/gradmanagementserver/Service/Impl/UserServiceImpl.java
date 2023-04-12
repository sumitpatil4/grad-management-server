package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Jwt.JwtTokenUtil;
import com.example.gradmanagementserver.Model.Notification;
import com.example.gradmanagementserver.Model.User;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> login(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        Map<String,String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(payload, new TypeReference<Map<String, String>>() {});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String ROLE = "ROLE_USER";
        Map<String,Object> response = new HashMap<>();
        Optional<User> user = userRepository.findById(map.get("sub"));
        if(user.equals(Optional.empty())){
            User newUser = new User(
                    map.get("sub"),
                    map.get("name"),
                    map.get("email"),
                    map.get("picture"),
                    ROLE
                    );
            userRepository.save(newUser);
            response.put("message ",newUser.getRole()+" Logged In");
            response.put("user",newUser);
        }
        else{
            response.put("message ","Login Successful");
            response.put("user",user.get());
        }
        User newUser = (User) response.get("user");
        String accessToken = jwtTokenUtil.generateAccessToken(newUser);
        response.put("accessToken",accessToken);
        return response;
    }

    @Override
    public Map<String, Object> getUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> userList= userRepository.findAll();
        response.put("message","Users Fetched");
        response.put("userList",userList);
        return response;
    }

    @Override
    public Map<String, Object> updateRole(String role, User user) {
        Map<String, Object> response = new HashMap<>();
        User newUser = userRepository.findById(user.getUserId()).get();
        newUser.setRole(role);
        userRepository.save(newUser);
        response.put("message","Role Updated");
        response.put("user",newUser);
        return response;
    }
}
