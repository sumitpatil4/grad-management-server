package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.AttendanceDto;
import org.springframework.http.ResponseEntity;

public interface AttendanceService {

    public ResponseEntity<?> createAttendance(Integer meetingId, AttendanceDto attendanceDto);
}
