package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.Student;
import com.gdpu.thesismanagementplatform.pojo.Teacher;
import com.gdpu.thesismanagementplatform.repository.StudentRepository;
import com.gdpu.thesismanagementplatform.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
