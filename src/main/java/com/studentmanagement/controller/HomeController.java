package com.studentmanagement.controller;

import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for Home page
 */
@Controller
public class HomeController {

    private final StudentService studentService;
    private final CourseService courseService;

    public HomeController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Display home page with navigation
     */
    @GetMapping("/")
    public String home(Model model) {
        long studentCount = studentService.getAllStudents().size();
        long courseCount = courseService.getAllCourses().size();
        
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("courseCount", courseCount);
        
        return "index";
    }
}
