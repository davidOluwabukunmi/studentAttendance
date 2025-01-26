package com.studentAttendance.repository;

import com.studentAttendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Boolean existsByStudent(Attendance student);
}
