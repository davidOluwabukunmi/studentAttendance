package com.studentAttendance.service;

import com.studentAttendance.dto.request.AttendanceDTO;
import com.studentAttendance.dto.request.StudentDTO;
import com.studentAttendance.dto.response.ApiResponse;
import com.studentAttendance.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    // Create
    ResponseEntity<ApiResponse<StudentDTO>> createStudent(StudentDTO request);

    // Read
    ResponseEntity<ApiResponse<StudentDTO>> getStudentById(Long id);
    ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentLists();
  //  ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByCourse(Long courseId);
    ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByDepartment(String department);
    ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByFaculty(String faculty);
    ResponseEntity<ApiResponse<List<StudentDTO>>> searchStudents(String query);

    // Update
    ResponseEntity<ApiResponse<StudentDTO>> updateStudent(Long id, StudentDTO request);

    // Delete
    void deleteById(Long id);

    // Attendance
    ResponseEntity<ApiResponse<List<AttendanceDTO>>> getStudentAttendance(Long studentId);

    // Course Enrollment
    ResponseEntity<ApiResponse<StudentDTO>> enrollStudentInCourse(Long studentId, Long courseId);
    ResponseEntity<ApiResponse<StudentDTO>> removeStudentFromCourse(Long studentId, Long courseId);
}