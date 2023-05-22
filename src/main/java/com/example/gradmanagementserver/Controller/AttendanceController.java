package com.example.gradmanagementserver.Controller;

import com.example.gradmanagementserver.Model.AttendanceDto;
import com.example.gradmanagementserver.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("createAttendance/{meetingId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createAttendance(@PathVariable Integer meetingId, @RequestBody AttendanceDto attendanceDto){
        return attendanceService.createAttendance(meetingId,attendanceDto);
    }
}
