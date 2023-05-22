package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.TopicRepository;
import com.example.gradmanagementserver.Repository.TrainerRepository;
import com.example.gradmanagementserver.Repository.UserRepository;
import com.example.gradmanagementserver.Service.TrainerService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public ResponseEntity<?> createTrainer(String userId, Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        User user;
        List<Trainer> trainerList;
        List<Trainer> filteredTrainer;
        Trainer newTrainer;
        try{
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByEmail(trainer.getEmail());
            filteredTrainer = trainerList.stream().filter(trainer1 -> trainer1.getUser().getUserId().equals(userId)).collect(Collectors.toList());
            if(filteredTrainer.size()!=0){
                newTrainer = filteredTrainer.get(0);
            }
            else{
                newTrainer = null;
            }
            if(newTrainer==null){
                newTrainer = newTrainer = new Trainer();
                newTrainer.setActive(true);
                newTrainer.setTrainerName(trainer.getTrainerName());
                newTrainer.setSkill(trainer.getSkill());
                newTrainer.setEmail(trainer.getEmail());
                newTrainer.setPhoneNumber(trainer.getPhoneNumber());
                newTrainer.setUser(user);
                trainerRepository.save(newTrainer);
                response.put("message", "Trainer created");
            }
            else if(newTrainer!=null && newTrainer.isActive()==false) {
                newTrainer.setActive(true);
                newTrainer.setTrainerName(trainer.getTrainerName());
                newTrainer.setSkill(trainer.getSkill());
                newTrainer.setPhoneNumber(trainer.getPhoneNumber());
                trainerRepository.save(newTrainer);
                response.put("message", "Trainer set as active");
            }
            else{
                response.put("message","Trainer already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTrainerById(String userId) {
        Map<String,Object> response = new HashMap<>();
        User user;
        List<Trainer> trainerList;
        List<Trainer> filteredList;
        try{
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByUser(user);
            filteredList = trainerList.stream().filter(trainer -> trainer.isActive()).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Trainers Fetched");
        response.put("trainers",filteredList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTrainer(Trainer trainer) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer;
        try{
            newTrainer = trainerRepository.findById(trainer.getTrainerId()).get();
            newTrainer.setTrainerName(trainer.getTrainerName());
            newTrainer.setEmail(trainer.getEmail());
            newTrainer.setSkill(trainer.getSkill());
            newTrainer.setPhoneNumber(trainer.getPhoneNumber());
            trainerRepository.save(newTrainer);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Updated Trainer");
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTrainer(Integer trainerId) {
        Map<String,Object> response = new HashMap<>();
        Trainer newTrainer;
        try{
            newTrainer = trainerRepository.findById(trainerId).get();
            newTrainer.setActive(false);
            trainerRepository.save(newTrainer);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Deleted Trainer");
        response.put("trainer",newTrainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTrainersByAvlAndSkill(String userId, TrainerDto trainerDto) {
        Map<String, Object> response = new HashMap<>();
        Set<Object> result=new HashSet<>();
        User user;
        String topicName;
        Topic topic;
        List<Trainer> trainerList;
        List<Trainer> filteredList;
        List<Trainer> finalList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates = new ArrayList<>();
        for(Date d : trainerDto.getDateList()){
            dates.add(df.format(d));
        }
        try {
            topic = topicRepository.findById(trainerDto.getTopicId()).get();
            topicName = topic.getTopicName();
            user = userRepository.findById(userId).get();
            trainerList = trainerRepository.findByUser(user);
            //Trainers found by topic name
            filteredList = trainerList.stream().filter(trainer ->trainer.isActive() && trainer.getSkill().equalsIgnoreCase(topicName)).collect(Collectors.toList());
            for (Trainer t : filteredList) {
                List<Availability> availabilityList = t.getAvailabilityList();
                for (Availability a : availabilityList) {
                    if(a.isActive()==false){
                        continue;
                    }
                    String d2 = df.format(a.getDate());
                    if (dates.contains(d2)) {
                        finalList.add(t);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        for(String d : dates) {
            Map<String, Object> tempMap = new HashMap<>();
            List<Trainer> trainerList1 = new ArrayList<>();
            tempMap.put("date", d);

            for (Trainer t : finalList) {
                List<Availability> availabilityList = t.getAvailabilityList();
                List<Availability> availabilityList1;
                availabilityList1 = availabilityList.stream().filter(availability ->
                        availability.isActive() &&
                     d.equals(df.format(availability.getDate()))
                ).collect(Collectors.toList());
                if(availabilityList1.size()!=0){
                    Trainer tempTrainer = new Trainer(t);
                    tempTrainer.setAvailabilityList(availabilityList1);
                    trainerList1.add(tempTrainer);
                }
            }
            tempMap.put("trainer",trainerList1);
            result.add(tempMap);
        }
        response.put("message", "Trainers Fetched");
        response.put("trainers", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> sendMails(MultipartFile file,String mailList) {
        String[] mailString=mailList.split(",");
        Map<String,Object> response = new HashMap<>();
        try{
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom("saikrupananda21@gmail.com");
            mimeMessageHelper.setTo(mailString);
            mimeMessageHelper.setSubject("Trainers Excel Sheet");
            mimeMessageHelper.setText("The attached excel sheet contains details of all the trainers with their availability");

            byte[] arr= file.getBytes();
            File outputFile = new File("TrainerFile.xlsx");
            OutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(arr);
            outputStream.close();

            FileSystemResource fileSystemResource= new FileSystemResource(outputFile);
            System.out.println(fileSystemResource);
            mimeMessageHelper.addAttachment("TrainersFile.xlsx",fileSystemResource);
            javaMailSender.send(mimeMessage);
            System.out.println("Mail with attachment sent successfully..");
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Sent Mails Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
