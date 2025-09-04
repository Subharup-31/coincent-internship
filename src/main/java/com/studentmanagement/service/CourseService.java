package com.studentmanagement.service;

import com.studentmanagement.entity.Course;
import com.studentmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Course operations
 */
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Create a new course
     */
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Update an existing course
     */
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
        
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        
        return courseRepository.save(existingCourse);
    }

    /**
     * Get course by ID
     */
    @Transactional(readOnly = true)
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    /**
     * Get all courses
     */
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Get courses enrolled by a student
     */
    @Transactional(readOnly = true)
    public List<Course> getCoursesByStudent(Long studentId) {
        return courseRepository.findByStudentId(studentId);
    }

    /**
     * Get courses not enrolled by a student
     */
    @Transactional(readOnly = true)
    public List<Course> getCoursesNotEnrolledByStudent(Long studentId) {
        return courseRepository.findNotEnrolledByStudent(studentId);
    }

    /**
     * Delete course by ID
     */
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Course not found with ID: " + id);
        }
        
        courseRepository.deleteById(id);
    }

    /**
     * Check if course exists
     */
    @Transactional(readOnly = true)
    public boolean courseExists(Long id) {
        return courseRepository.existsById(id);
    }
}
