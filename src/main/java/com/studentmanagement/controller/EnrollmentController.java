package com.studentmanagement.controller;

import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Student;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Enrollment operations
 */
@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Display student details with course enrollment options
     */
    @GetMapping("/student/{studentId}")
    public String viewStudentEnrollments(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        
        List<Course> enrolledCourses = courseService.getCoursesByStudent(studentId);
        List<Course> availableCourses = courseService.getCoursesNotEnrolledByStudent(studentId);
        
        model.addAttribute("student", student);
        model.addAttribute("enrolledCourses", enrolledCourses);
        model.addAttribute("availableCourses", availableCourses);
        
        return "enrollments/student";
    }

    /**
     * Enroll student in a course
     */
    @PostMapping("/student/{studentId}/enroll/{courseId}")
    public String enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId,
                                       RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.getStudentById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));
            Course course = courseService.getCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found"));
            
            student.addCourse(course);
            studentService.updateStudent(studentId, student);
            
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Student enrolled in course successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/enrollments/student/" + studentId;
    }

    /**
     * Unenroll student from a course
     */
    @PostMapping("/student/{studentId}/unenroll/{courseId}")
    public String unenrollStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId,
                                           RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.getStudentById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));
            Course course = courseService.getCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found"));
            
            student.removeCourse(course);
            studentService.updateStudent(studentId, student);
            
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Student unenrolled from course successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/enrollments/student/" + studentId;
    }
}
