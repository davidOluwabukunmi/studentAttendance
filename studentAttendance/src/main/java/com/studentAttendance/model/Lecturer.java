package com.studentAttendance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "lecturer")
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String staffId;
    private String department;
    private String phoneNumber;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses;




}
