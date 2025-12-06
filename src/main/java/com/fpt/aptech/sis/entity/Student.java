package com.fpt.aptech.sis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student_t")
public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "student_code", length = 20, nullable = false)
    private String studentCode;
    
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    
    @Column(name = "address", length = 255)
    private String address;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentScore> studentScores;
    
    public Student() {
    }
    
    public Student(String studentCode, String fullName, String address) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
    }
    
    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentCode() {
        return studentCode;
    }
    
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<StudentScore> getStudentScores() {
        return studentScores;
    }
    
    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores = studentScores;
    }
}

