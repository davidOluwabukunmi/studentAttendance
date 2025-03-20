package com.studentAttendance.dto.request;

import com.studentAttendance.model.Attendance;
import com.studentAttendance.model.Course;
import com.studentAttendance.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceDTO {
    private Long studentId;
    private Long courseId;
    private Attendance.Status status;
}
