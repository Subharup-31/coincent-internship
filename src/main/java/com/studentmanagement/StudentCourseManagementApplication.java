package com.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main application class for Student Course Management System
 * 
 * @author Student Management Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class StudentCourseManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentCourseManagementApplication.class, args);
    }
}
