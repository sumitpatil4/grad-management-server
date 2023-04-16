package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.MeetingDto;
import com.example.gradmanagementserver.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;
    @PostMapping("/createMeeting")
    public ResponseEntity<?> createMeeting(@RequestBody MeetingDto meetingDto){
        return meetingService.createMeeting(meetingDto);
    }

    @GetMapping("/getMeetings/{trainingId}")
    public ResponseEntity<?> getMeetings(@PathVariable Integer trainingId){
        return meetingService.getMeetings(trainingId);
    }

    @DeleteMapping("/deleteMeeting/{meetingId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> deleteMeeting(@PathVariable Integer meetingId){
        return meetingService.deleteMeeting(meetingId);
    }

    @PutMapping("/updateMeeting")
    @CrossOrigin(value = "**")
    public ResponseEntity<?> updateMeeting(@RequestBody MeetingDto meetingDto){
        return meetingService.updateMeeting(meetingDto);
    }
}
