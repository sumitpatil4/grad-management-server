package com.example.gradmanagementserver.Repository;

import com.example.gradmanagementserver.Model.Attendance;
import com.example.gradmanagementserver.Model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    public List<Attendance> findByMeeting(Meeting meeting);
}
