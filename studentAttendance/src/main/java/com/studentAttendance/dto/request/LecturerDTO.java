package com.studentAttendance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String staffId;
    private String department;
    private String phoneNumber;
}
