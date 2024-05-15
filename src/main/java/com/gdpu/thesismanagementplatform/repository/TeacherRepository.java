package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByTeacherNameAndPassword(String teacherName, String password);

    Teacher findByTeacherId(int teacherId);

    Teacher findByTeacherName(String username);

    Teacher findByTeacherIdAndTeacherName(int teacherId, String teacherName);
}