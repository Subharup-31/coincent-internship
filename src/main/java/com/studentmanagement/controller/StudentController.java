package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Student operations
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Display list of students
     */
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list";
    }

    /**
     * Display student details
     */
    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        
        model.addAttribute("student", student);
        return "students/view";
    }

    /**
     * Display form to create new student
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    /**
     * Display form to edit existing student
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        
        model.addAttribute("student", student);
        return "students/form";
    }

    /**
     * Create new student
     */
    @PostMapping
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            Student createdStudent = studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Student created successfully!");
            return "redirect:/students/" + createdStudent.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/students/new";
        }
    }

    /**
     * Update existing student
     */
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, 
                               RedirectAttributes redirectAttributes) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Student updated successfully!");
            return "redirect:/students/" + updatedStudent.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/students/" + id + "/edit";
        }
    }

    /**
     * Delete student
     */
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Student deleted successfully!");
            return "redirect:/students";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/students/" + id;
        }
    }
}
