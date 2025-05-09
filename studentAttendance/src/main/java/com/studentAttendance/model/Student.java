package com.studentAttendance.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String matricNo;
    private String department;
    private String faculty;
    private String level;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

}
