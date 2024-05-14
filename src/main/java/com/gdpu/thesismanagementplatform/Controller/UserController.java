package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.pojo.Student;
import com.gdpu.thesismanagementplatform.pojo.Teacher;
import com.gdpu.thesismanagementplatform.pojo.User;
import com.gdpu.thesismanagementplatform.repository.StudentRepository;
import com.gdpu.thesismanagementplatform.repository.TeacherRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController {
    //接收登录请求{username，password，role}，用户类型1，2，3，代表管理员，老师，学生，判断用户名密码是否正确，是否存在与数据库中，返回首页
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    public void login(@RequestBody User user, HttpServletResponse response, HttpSession session) throws IOException {
        //清除session中的username
        session.removeAttribute("username");
        if(Objects.equals(user.getRole(), "1")){
            System.out.println("admin");
            //管理员界面
            if(Objects.equals(user.getUsername(), "admin") && Objects.equals(user.getPassword(), "admin")){
                response.setHeader("location", "/admin");
            }else{
                response.getWriter().write("error");
            }
        }else if(Objects.equals(user.getRole(), "2")){
            //判断教师用户账号密码是否正确，返回首页，在response中写入cookie，存储用户信息，传入前端，显示当前用户类型：用户名
            //在数据库中查找教师用户信息，判断是否存在，存在则返回首页，不存在则返回登录界面
            Teacher teacher = teacherRepository.findByTeacherNameAndPassword(user.getUsername(), user.getPassword());
            if(teacher != null){
                System.out.println("teacher:"+teacher);
            }else{
                response.getWriter().write("error");
            }
        }
        else if(Objects.equals(user.getRole(), "3")){
            //判断学生用户账号密码是否正确，返回首页，在response中写入cookie，存储用户信息，传入前端，显示当前用户类型：用户名
            //在数据库中查找学生用户信息，判断是否存在，存在则返回首页，不存在则返回登录界面
            Student student = studentRepository.findByStudentNameAndPassword(user.getUsername(), user.getPassword());
            System.out.println(user);
            if(student != null){
                System.out.println("student:"+student);
                //跳转到首页，并将用户信息传入前端,response.headers.location;
                //保存用户名到session中
                session.setAttribute("username", user.getUsername());

                response.setHeader("location", "/");
            }else{
                System.out.println("error");
                response.getWriter().write("error");
            }
        }
        else{
            System.out.println("error");
        }
    }
    @RequestMapping("/getUsername")
    @ResponseBody
    public String getUsername(HttpSession session) {
        return (String) session.getAttribute("username");
    }
    @RequestMapping("/User/Register")
    public String register() {
        return "/User/Register";
    }

    @RequestMapping("/User/RegisterCheck")
    public void registerCheck(@RequestBody Student student, HttpServletResponse response, HttpSession session) throws IOException {
        //注册检查,判断学生学号是否存在，存在则返回注册界面，不存在则注册成功，返回登录界面
        if(studentRepository.findByStudentId(student.getStudentId())!=null) {
            response.getWriter().write("用户已存在");
        }else{
            studentRepository.save(student);
            //把学生信息存入session
            session.setAttribute("username", student.getStudentName());
            response.getWriter().write("注册成功");
            response.setHeader("location", "/");
        }
    }
}
