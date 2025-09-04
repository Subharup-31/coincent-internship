package com.studentmanagement.repository;

import com.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find student by email
     */
    Optional<Student> findByEmail(String email);

    /**
     * Check if student exists by email
     */
    boolean existsByEmail(String email);

    /**
     * Find students enrolled in a specific course
     */
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> findByCourseId(@Param("courseId") Long courseId);

    /**
     * Find students not enrolled in a specific course
     */
    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT s2.id FROM Student s2 JOIN s2.courses c WHERE c.id = :courseId)")
    List<Student> findNotEnrolledInCourse(@Param("courseId") Long courseId);
}
