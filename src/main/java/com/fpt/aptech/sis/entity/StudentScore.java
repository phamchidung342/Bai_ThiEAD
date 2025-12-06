package com.fpt.aptech.sis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "student_score_t")
public class StudentScore implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Integer studentScoreId;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @Column(name = "score1", precision = 5, scale = 2)
    private BigDecimal score1;
    
    @Column(name = "score2", precision = 5, scale = 2)
    private BigDecimal score2;
    
    public StudentScore() {
    }
    
    public StudentScore(Student student, Subject subject, BigDecimal score1, BigDecimal score2) {
        this.student = student;
        this.subject = subject;
        this.score1 = score1;
        this.score2 = score2;
    }
    
    // Getters and Setters
    public Integer getStudentScoreId() {
        return studentScoreId;
    }
    
    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public BigDecimal getScore1() {
        return score1;
    }
    
    public void setScore1(BigDecimal score1) {
        this.score1 = score1;
    }
    
    public BigDecimal getScore2() {
        return score2;
    }
    
    public void setScore2(BigDecimal score2) {
        this.score2 = score2;
    }
}

