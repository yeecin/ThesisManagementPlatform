package com.gdpu.thesismanagementplatform.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class ThesisChoice {
    @Id
    @Column(nullable = false)
    private int choiceId;

    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="thesis_id", nullable = false)
    private Thesis thesis;

    @Column(nullable = false)
    private ChoiceStatus status;

    @Column
    private String thesisFilePath;

    public enum ChoiceStatus {
        SUBMITTING,
        SELECTED,
        COMPLETED
    }
}