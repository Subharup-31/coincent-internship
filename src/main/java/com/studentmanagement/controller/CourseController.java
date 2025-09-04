package com.studentmanagement.controller;

import com.studentmanagement.entity.Course;
import com.studentmanagement.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Course operations
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Display list of courses
     */
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    /**
     * Display course details
     */
    @GetMapping("/{id}")
    public String viewCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
        
        model.addAttribute("course", course);
        return "courses/view";
    }

    /**
     * Display form to create new course
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/form";
    }

    /**
     * Display form to edit existing course
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
        
        model.addAttribute("course", course);
        return "courses/form";
    }

    /**
     * Create new course
     */
    @PostMapping
    public String createCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        try {
            Course createdCourse = courseService.createCourse(course);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Course created successfully!");
            return "redirect:/courses/" + createdCourse.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/courses/new";
        }
    }

    /**
     * Update existing course
     */
    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course, 
                              RedirectAttributes redirectAttributes) {
        try {
            Course updatedCourse = courseService.updateCourse(id, course);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Course updated successfully!");
            return "redirect:/courses/" + updatedCourse.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/courses/" + id + "/edit";
        }
    }

    /**
     * Delete course
     */
    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourse(id);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Course deleted successfully!");
            return "redirect:/courses";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/courses/" + id;
        }
    }
}
