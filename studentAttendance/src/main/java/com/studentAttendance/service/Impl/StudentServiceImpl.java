package com.studentAttendance.service.Impl;

import com.studentAttendance.dto.request.AttendanceDTO;
import com.studentAttendance.dto.request.StudentDTO;
import com.studentAttendance.dto.response.ApiResponse;
import com.studentAttendance.exception.ResourceNotFoundException;
import com.studentAttendance.model.Course;
import com.studentAttendance.model.Student;
import com.studentAttendance.repository.AttendanceRepository;
import com.studentAttendance.repository.CourseRepository;
import com.studentAttendance.repository.StudentRepository;
import com.studentAttendance.service.StudentService;
import com.studentAttendance.utils.AppCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final AttendanceRepository attendanceRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AttendanceRepository attendanceRepository,CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(StudentDTO request) {
        if (request.getCourseIds() == null || request.getCourseIds().isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.<StudentDTO>builder()
                    .code(AppCode.NOT_FOUND.getCode())
                    .message("Course IDs cannot be null or empty")
                    .build());
        }


        List<Course> courses = courseRepository.findAllById(request.getCourseIds());
        if (courses.size() != request.getCourseIds().size()) {
            throw new ResourceNotFoundException("One or more courses not found.");
        }


        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .level(request.getLevel())
                .department(request.getDepartment())
                .dateOfBirth(request.getDateOfBirth())
                .courses(courses)
                .faculty(request.getFaculty())
                .matricNo(request.getMatricNo())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();

        studentRepository.save(student);

        StudentDTO responseDto = StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .level(student.getLevel())
                .email(student.getEmail())
                .department(student.getDepartment())
                .faculty(student.getFaculty())
                .phoneNumber(student.getPhoneNumber())
                .dateOfBirth(student.getDateOfBirth())
                .courseIds( student.getCourses().stream().map(Course::getId).collect(Collectors.toList()))
                .build();


        return ResponseEntity.ok(ApiResponse.<StudentDTO>builder()
                        .data(responseDto)
                        .code(AppCode.OKAY.getCode())
                        .message(AppCode.OKAY.getMessage())
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        StudentDTO responseData = StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .level(student.getLevel())
                .email(student.getEmail())
                .department(student.getDepartment())
                .faculty(student.getFaculty())
                .phoneNumber(student.getPhoneNumber())
                .dateOfBirth(student.getDateOfBirth())
                .courseIds(student.getCourses().stream().map(Course::getId).collect(Collectors.toList()))
                .build();


        return ResponseEntity.ok(ApiResponse.<StudentDTO>builder()
                        .data(responseData)
                        .code(AppCode.OKAY.getCode())
                        .message(AppCode.OKAY.getMessage())
                .build());
    }


    @Override
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentLists() {
        // Fetch all students from the repository
        List<Student> students = studentRepository.findAll();

        // Convert List<Student> to List<StudentDTO>
        List<StudentDTO> studentDTOs = students.stream()
                .map(student -> StudentDTO.builder()
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .email(student.getEmail())
                        .department(student.getDepartment())
                        .faculty(student.getFaculty())
                        .level(student.getLevel())
                        .phoneNumber(student.getPhoneNumber())
                        .dateOfBirth(student.getDateOfBirth())
                        .courseIds(student.getCourses().stream()
                                .map(Course::getId)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        // Build and return the response
        return ResponseEntity.ok(
                ApiResponse.<List<StudentDTO>>builder()
                        .data(studentDTOs)
                        .code(AppCode.OKAY.getCode())
                        .message(AppCode.OKAY.getMessage())
                        .build()
        );
    }

//    @Override
//    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByCourse(Long courseId) {
//        List<Student> students = studentRepository.findByCourseId(courseId);
//        return null;
//    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByDepartment(String department) {
       List<Student> students = studentRepository.findByDepartment(department);

       List<StudentDTO> studentDTOs =  students.stream().map(student -> StudentDTO.builder()
               .firstName(student.getFirstName())
               .lastName(student.getLastName())
               .email(student.getEmail())
               .department(student.getDepartment())
               .faculty(student.getFaculty())
               .level(student.getLevel())
               .phoneNumber(student.getPhoneNumber())
               .dateOfBirth(student.getDateOfBirth())
               .courseIds(student.getCourses().stream().map(Course::getId).collect(Collectors.toList()))
               .build()) .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.<List<StudentDTO>>builder()
                .data(studentDTOs)
                .code(AppCode.OKAY.getCode())
                .message(AppCode.OKAY.getMessage())
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByFaculty(String faculty) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDTO>>> searchStudents(String query) {
        List<Student> students = studentRepository.findByFirstNameContainingOrLastNameContaining(query, query);

        List<StudentDTO> studentDTOs = students.stream()
                .map(student -> StudentDTO.builder()
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .email(student.getEmail())
                        .department(student.getDepartment())
                        .faculty(student.getFaculty())
                        .level(student.getLevel())
                        .phoneNumber(student.getPhoneNumber())
                        .dateOfBirth(student.getDateOfBirth())
                        .courseIds(student.getCourses().stream().map(Course::getId).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.<List<StudentDTO>>builder()
                .data(studentDTOs)
                .code(AppCode.OKAY.getCode())
                .message(AppCode.OKAY.getMessage())
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(Long id, StudentDTO request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setFaculty(request.getFaculty());
        student.setLevel(request.getLevel());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDateOfBirth(request.getDateOfBirth());

        if (request.getCourseIds() != null && !request.getCourseIds().isEmpty()) {
            List<Course> courses = courseRepository.findAllById(request.getCourseIds());
            if (courses.size() != request.getCourseIds().size()) {
                throw new ResourceNotFoundException("One or more courses not found.");
            }
            student.setCourses(courses);
        }

        studentRepository.save(student);

        StudentDTO responseDto = StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .level(student.getLevel())
                .email(student.getEmail())
                .department(student.getDepartment())
                .faculty(student.getFaculty())
                .phoneNumber(student.getPhoneNumber())
                .dateOfBirth(student.getDateOfBirth())
                .courseIds(student.getCourses().stream().map(Course::getId).collect(Collectors.toList()))
                .build();

        return ResponseEntity.ok(ApiResponse.<StudentDTO>builder()
                .data(responseDto)
                .code(AppCode.OKAY.getCode())
                .message(AppCode.OKAY.getMessage())
                .build());
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<ApiResponse<List<AttendanceDTO>>> getStudentAttendance(Long studentId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> enrollStudentInCourse(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> removeStudentFromCourse(Long studentId, Long courseId) {
        return null;
    }


}
