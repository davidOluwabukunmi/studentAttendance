package com.studentAttendance.repository;

import com.studentAttendance.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer,Long> {
    Boolean existsByName(String name);
}
