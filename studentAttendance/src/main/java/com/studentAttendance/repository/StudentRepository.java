package com.studentAttendance.repository;

import com.studentAttendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Boolean existsByName(String name);
    Boolean existsByMatricNo(String matricNo);
    List<Student> findByDepartment(String department);
    List<Student> findByFaculty(String faculty);
    List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
//    List<Student> findByCourse_Id(Long course);
}
