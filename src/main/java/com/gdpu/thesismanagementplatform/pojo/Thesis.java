package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
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

    private String content;

}