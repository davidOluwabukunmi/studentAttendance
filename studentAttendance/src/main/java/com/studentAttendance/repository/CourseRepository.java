package com.studentAttendance.repository;

import com.studentAttendance.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Boolean existsByName(String name);
}
