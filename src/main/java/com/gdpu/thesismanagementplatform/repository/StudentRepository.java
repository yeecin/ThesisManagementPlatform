package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentNameAndPassword(String studentName, String password);
    Student findByStudentName(String studentName);

    Student findByStudentId(int studentId);
}