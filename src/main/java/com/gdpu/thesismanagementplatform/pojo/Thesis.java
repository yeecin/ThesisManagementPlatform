package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Thesis {
    @Id
    @Column(nullable = false)
    private int thesisId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student student;

    private String content;

}