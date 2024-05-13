package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Thesis {
    //论文id，论文题目，学生id，教师id，论文内容，论文状态
    @Id
    @Column(nullable = false)
    private int thesisId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int studentId;
    @Column(nullable = false)
    private int teacherId;
    private String content;
    private String status;
}
