package com.studentmanagement.service;

import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Student operations
 */
@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Create a new student
     */
    public Student createStudent(Student student) {
        // Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        }
        
        return studentRepository.save(student);
    }

    /**
     * Update an existing student
     */
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        
        // Check if email is being changed and if new email already exists
        if (!existingStudent.getEmail().equals(student.getEmail()) && 
            studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        }
        
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        
        return studentRepository.save(existingStudent);
    }

    /**
     * Get student by ID
     */
    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Get all students
     */
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Get students enrolled in a course
     */
    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourse(Long courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    /**
     * Get students not enrolled in a course
     */
    @Transactional(readOnly = true)
    public List<Student> getStudentsNotEnrolledInCourse(Long courseId) {
        return studentRepository.findNotEnrolledInCourse(courseId);
    }

    /**
     * Delete student by ID
     */
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
        
        studentRepository.deleteById(id);
    }

    /**
     * Check if student exists
     */
    @Transactional(readOnly = true)
    public boolean studentExists(Long id) {
        return studentRepository.existsById(id);
    }
}
