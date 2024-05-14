package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.Student;
import com.gdpu.thesismanagementplatform.pojo.Teacher;
import com.gdpu.thesismanagementplatform.repository.StudentRepository;
import com.gdpu.thesismanagementplatform.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/getStudents")
    @ResponseBody
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    @RequestMapping("/getTeachers")
    @ResponseBody
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
    @RequestMapping("/searchStudentById")
    @ResponseBody
    public Student searchStudent(@Param("studentId") int studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    @RequestMapping("/searchTeacherById")
    @ResponseBody
    public Teacher searchTeacher(@Param("teacherId") int teacherId) {
        return teacherRepository.findByTeacherId(teacherId);
    }
}
