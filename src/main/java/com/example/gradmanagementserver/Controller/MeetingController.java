package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.MeetingDto;
import com.example.gradmanagementserver.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;
    @PostMapping("/createMeeting")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createMeeting(@RequestBody MeetingDto meetingDto){
        return meetingService.createMeeting(meetingDto);
    }

    @GetMapping("/getMeetings/{trainingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_LEADER')")
    public ResponseEntity<?> getMeetings(@PathVariable Integer trainingId){
        return meetingService.getMeetings(trainingId);
    }

    @DeleteMapping("/deleteMeeting/{meetingId}")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteMeeting(@PathVariable Integer meetingId){
        return meetingService.deleteMeeting(meetingId);
    }

    @PutMapping("/updateMeeting")
    @CrossOrigin(value = "**")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateMeeting(@RequestBody MeetingDto meetingDto){
        return meetingService.updateMeeting(meetingDto);
    }

    @GetMapping("/getMeetingsByIntern/{internId}")
    public ResponseEntity<?> getMeetingsInterns(@PathVariable Integer internId){
        return meetingService.getMeetingsInterns(internId);
    }
}
