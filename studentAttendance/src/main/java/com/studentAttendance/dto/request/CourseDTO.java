package com.studentAttendance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private String courseCode;
    private String courseTitle;
    private int creditUnit;
    private Long lecturerId;
    private List<Long> studentIds;
}
