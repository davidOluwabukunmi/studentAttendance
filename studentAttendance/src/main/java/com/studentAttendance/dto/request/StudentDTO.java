package com.studentAttendance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String matricNo;
    private String department;
    private String faculty;
    private String level;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private List<Long> courseIds;
}
