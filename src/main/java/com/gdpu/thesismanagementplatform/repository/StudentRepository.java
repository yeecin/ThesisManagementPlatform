package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentNameAndPassword(String studentName, String password);
    Student findByStudentName(String studentName);
    Student findByStudentId(int studentId);

}