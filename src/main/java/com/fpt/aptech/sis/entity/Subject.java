package com.fpt.aptech.sis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subject_t")
public class Subject implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer subjectId;
    
    @Column(name = "subject_code", length = 20, nullable = false)
    private String subjectCode;
    
    @Column(name = "subject_name", length = 100, nullable = false)
    private String subjectName;
    
    @Column(name = "credit", nullable = false)
    private Integer credit;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentScore> studentScores;
    
    public Subject() {
    }
    
    public Subject(String subjectCode, String subjectName, Integer credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }
    
    // Getters and Setters
    public Integer getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public Integer getCredit() {
        return credit;
    }
    
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
    
    public List<StudentScore> getStudentScores() {
        return studentScores;
    }
    
    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores = studentScores;
    }
}

