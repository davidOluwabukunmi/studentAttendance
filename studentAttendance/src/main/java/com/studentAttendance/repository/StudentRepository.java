package com.studentAttendance.repository;

import com.studentAttendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Boolean existsByName(String name);
}
