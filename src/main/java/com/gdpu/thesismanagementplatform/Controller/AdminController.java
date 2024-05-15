package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.*;
import com.gdpu.thesismanagementplatform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ThesisChoiceRepository thesisChoiceRepository;

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
    @RequestMapping("/getThesisTitles")
    @ResponseBody
    public List<Thesis> getThesisTitles() {
        return thesisRepository.findAll();
    }
    @RequestMapping("/updateStudent")
    @ResponseBody
    public Student updateStudent(@Param("studentId") int studentId, @Param("studentName") String studentName, @Param("studentPassword") String studentPassword) {
        Student student = studentRepository.findByStudentId(studentId);
        student.setStudentName(studentName);
        student.setPassword(studentPassword);
        return studentRepository.save(student);
    }
    @PostMapping("/postAnnouncement")
    public Announcement postAnnouncement(@RequestBody Announcement announcement) {
        return announcementRepository.save(announcement);
    }
    @PostMapping("getCurrentAnnouncement")
    @ResponseBody
    public Announcement getCurrentAnnouncement() {
        return announcementRepository.findTopByOrderByIdDesc();
    }
    @GetMapping("/getAnnouncements")
    @ResponseBody
    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAll();
    }
    @PutMapping("/updateAnnouncement")
    @ResponseBody
    public Announcement updateAnnouncement(@RequestBody Announcement announcement) {
        return announcementRepository.save(announcement);
    }
    @GetMapping("/getThesisChoices")
    @ResponseBody
    public List<ThesisChoice> getThesisChoices() {
        return thesisChoiceRepository.findAll();
    }
    @PutMapping("/updateThesisChoice")
    @ResponseBody
    public ThesisChoice updateThesisChoice(@RequestBody ThesisChoice thesisChoice) {
        return thesisChoiceRepository.save(thesisChoice);
    }
}
