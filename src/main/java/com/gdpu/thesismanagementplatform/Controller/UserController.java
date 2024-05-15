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
                response.getWriter().write("success");
                response.setHeader("location", "/admin");
            }else{
                response.getWriter().write("fail");
            }
        }else if(Objects.equals(user.getRole(), "2")){
            //判断教师用户账号密码是否正确，返回首页，在response中写入cookie，存储用户信息，传入前端，显示当前用户类型：用户名
            //在数据库中查找教师用户信息，判断是否存在，存在则返回首页，不存在则返回登录界面
            Teacher teacher = teacherRepository.findByTeacherNameAndPassword(user.getUsername(), user.getPassword());
            if(teacher != null){
                System.out.println("teacher:"+teacher);
                session.setAttribute("username", user.getUsername());
                response.getWriter().write("success");
                response.setHeader("location", "/Teacher/index");
            }else{
                response.getWriter().write("fail");
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
                response.getWriter().write("success");
                response.setHeader("location", "/");
            }else{
                System.out.println("error");
                response.getWriter().write("fail");
                response.setHeader("location", "/login");
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

    @RequestMapping("/Teacher/Register")
    public String teacherRegister() {
        return "/Teacher/Register";
    }

    @RequestMapping("/User/ResetPassword")
    public String studentResetPassword() {
        return "/User/ResetPasswords";
    }
    @RequestMapping("/Teacher/ResetPassword")
    public String teacherResetPassword() {
        return "/Teacher/ResetPasswords";
    }

    @RequestMapping("/Teacher/index")
    public String teacherIndex() {
        return "/Teacher/index";
    }

    @RequestMapping("/User/RegisterCheck")
    public void registerCheck(@RequestBody Student student, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //注册检查,判断学生学号是否存在，存在则返回注册界面，不存在则注册成功，返回登录界面
        if(studentRepository.findByStudentId(student.getStudentId())!=null) {
            response.getWriter().write("用户学号已存在");

        }else{
            studentRepository.save(student);
            response.getWriter().write("注册成功");
            //把学生信息存入session
            session.setAttribute("username", student.getStudentName());
            response.setHeader("location", "/");
        }
    }
    @RequestMapping("/Teacher/RegisterCheck")
    public void registerCheck(@RequestBody Teacher teacher, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //注册检查,判断教师编号是否存在，存在则返回注册界面，不存在则注册成功，返回登录界面
        if(teacherRepository.findByTeacherId(teacher.getTeacherId())!=null) {
            response.getWriter().write("教师编号已存在");
        }else{
            teacherRepository.save(teacher);
            response.getWriter().write("注册成功");
            //把教师信息存入session
            session.setAttribute("username", teacher.getTeacherName());
            response.setHeader("location", "/Teacher/index");
        }
    }

    @RequestMapping("/User/ResetPasswordCheck")
    public void resetStudentPassword(@RequestBody Student student, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if(studentRepository.findByStudentIdAndStudentName(student.getStudentId(), student.getStudentName()) == null) {
            response.getWriter().write("学号或姓名错误");
        } else {
            Student student1 = studentRepository.findByStudentIdAndStudentName(student.getStudentId(), student.getStudentName());
            student1.setPassword(student.getPassword());
            studentRepository.save(student1);
            response.getWriter().write("密码修改成功");

            //把学生信息存入session
            session.setAttribute("username", student.getStudentName());
            response.setHeader("location", "/login");
        }
    }

    @RequestMapping("/Teacher/ResetPasswordCheck")
    public void resetTeacherPassword(@RequestBody Teacher teacher, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if(teacherRepository.findByTeacherIdAndTeacherName(teacher.getTeacherId(), teacher.getTeacherName()) == null) {
            response.getWriter().write("教师编号或姓名错误");
        } else {
            Teacher teacher1 = teacherRepository.findByTeacherIdAndTeacherName(teacher.getTeacherId(), teacher.getTeacherName());
            teacher1.setPassword(teacher.getPassword());
            teacherRepository.save(teacher1);
            response.getWriter().write("密码修改成功");

            //把教师信息存入session
            session.setAttribute("username", teacher.getTeacherName());
            response.setHeader("location", "/login");
        }
    }

    @RequestMapping(value = "/User/UpdateProfile", method = RequestMethod.POST)
    public void updateProfile(@RequestBody Student student, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 根据用户名查找用户
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.getWriter().write("用户未登录");
        } else {
            Student student1 = studentRepository.findByStudentName(username);
            // 更新用户信息
            student1.setStudentName(student.getStudentName());
            student1.setEmail(student.getEmail());
            student1.setPhone(student.getPhone());
            studentRepository.save(student1);
            session.setAttribute("username", student.getStudentName());
            response.getWriter().write("更新成功");
        }
    }

    @RequestMapping(value = "/User/GetProfile", method = RequestMethod.GET)
    @ResponseBody
    public Student getProfile(HttpSession session) {
        String username = (String) session.getAttribute("username");

        // 根据用户名查找用户
        Student student = studentRepository.findByStudentName(username);
        if (student != null) {
            return student;
        }

        // 如果找不到用户，返回null
        return null;
    }
}
