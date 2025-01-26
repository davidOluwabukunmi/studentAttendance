package com.studentAttendance.repository;

import com.studentAttendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Boolean existsByName(String name);
}
