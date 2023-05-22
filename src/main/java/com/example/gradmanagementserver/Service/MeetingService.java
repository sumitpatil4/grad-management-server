package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.MeetingDto;
import org.springframework.http.ResponseEntity;

public interface MeetingService {
    public ResponseEntity<?> createMeeting(MeetingDto meetingDto);
    public ResponseEntity<?> getMeetings(Integer meetingId);
    public ResponseEntity<?> deleteMeeting(Integer meetingId);
    public ResponseEntity<?> updateMeeting(MeetingDto meetingDto);

    public ResponseEntity<?> getMeetingsInterns(Integer internId);
}
