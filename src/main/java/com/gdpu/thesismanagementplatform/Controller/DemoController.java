package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.Student;
import com.gdpu.thesismanagementplatform.pojo.Teacher;
import com.gdpu.thesismanagementplatform.pojo.User;
import com.gdpu.thesismanagementplatform.repository.StudentRepository;
import com.gdpu.thesismanagementplatform.repository.TeacherRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
public class DemoController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
//    @RequestMapping("/User/personal")
//    public String personal() {
//        return "User/personal";
//    }
    @RequestMapping("/User/personal")
    public ModelAndView personal() {
        ModelAndView modelAndView = new ModelAndView("User/personal");
        // Add any model attributes if needed
        return modelAndView;
    }
    @RequestMapping("/User/privacy")
    public String privacy() {
        return "User/privacy";
    }
    @RequestMapping("/User/agreement")
    public String agreement() {
        return "User/agreement";
    }
    @RequestMapping("/User/ResetPasswords")
    public String resetPasswords() {
        return "User/ResetPasswords";
    }
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 指定保存文件的路径
        Path path = Paths.get("uploads/" + file.getOriginalFilename());

        // 将文件保存到文件系统中
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "upload failure";
        }

        return "upload success";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

}
