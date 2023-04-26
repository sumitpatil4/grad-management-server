package com.example.gradmanagementserver.Service.Impl;

import com.example.gradmanagementserver.Model.*;
import com.example.gradmanagementserver.Repository.AttendanceRepository;
import com.example.gradmanagementserver.Repository.InternRepository;
import com.example.gradmanagementserver.Repository.MeetingRepository;
import com.example.gradmanagementserver.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private InternRepository internRepository;
    @Override
    public ResponseEntity<?> createAttendance(Integer meetingId, AttendanceDto attendanceDto) {
        Map<String,Object> response = new HashMap<>();
        Meeting meeting;
        Intern intern;
        try{
            meeting = meetingRepository.findById(meetingId).get();
            for(int i=0;i<attendanceDto.getIdList().size();i++){
                intern = internRepository.findById(attendanceDto.getIdList().get(i)).get();
                boolean res = attendanceDto.getAttendanceList().get(i);
                Attendance attendance = new Attendance();
                attendance.setIntern(intern);
                attendance.setMeeting(meeting);
                attendance.setPresent(res);
                attendanceRepository.save(attendance);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Attendance Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
