package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Teacher {
    //教师工号，姓名，邮箱，电话，密码
    @Id
    @Column(nullable = false)
    private int teacherId;
    @Column(nullable = false)
    private String teacherName;
    private String email;
    private String phone;
    private String password;

}
