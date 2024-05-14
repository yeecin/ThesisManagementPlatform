package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {//学号，姓名，专业，年级，邮箱，电话，密码
    @Id
    @Column(nullable = false)
    private int studentId;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false)
    private String major;
    private String grade;
    private String email;
    private String phone;
    private String password;
    @OneToMany
    private List<Thesis> theses;

}