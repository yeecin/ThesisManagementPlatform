package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.Announcement;
import com.gdpu.thesismanagementplatform.pojo.Image;
import com.gdpu.thesismanagementplatform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @RequestMapping("/announcement")
    public String announcement() {
        return "announcement";
    }
    @RequestMapping("/thesis-topic")
    public String thesisTopic() {
        return "thesis-topic";
    }
    @RequestMapping("/thesis")
    public String thesis() {
        return "thesis";
    }
    //从数据库中获取图片，传给前端
    @GetMapping("/getImages")
    public List<Image> getImages() {
        return imageRepository.findAll();
    }
    //从数据库中获取公告，传给前端
    @GetMapping("/getAnnouncements")
    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAll();
    }
}
