package com.studentmanagement.repository;

import com.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Course entity
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Find courses enrolled by a specific student
     */
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> findByStudentId(@Param("studentId") Long studentId);

    /**
     * Find courses not enrolled by a specific student
     */
    @Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT c2.id FROM Course c2 JOIN c2.students s WHERE s.id = :studentId)")
    List<Course> findNotEnrolledByStudent(@Param("studentId") Long studentId);
}
