package com.fpt.aptech.sis.servlet;

import com.fpt.aptech.sis.dao.StudentDAO;
import com.fpt.aptech.sis.dao.StudentScoreDAO;
import com.fpt.aptech.sis.dao.SubjectDAO;
import com.fpt.aptech.sis.entity.Student;
import com.fpt.aptech.sis.entity.StudentScore;
import com.fpt.aptech.sis.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ScoreServlet", urlPatterns = {"/score"})
public class ScoreServlet extends HttpServlet {
    
    private StudentScoreDAO studentScoreDAO;
    private StudentDAO studentDAO;
    private SubjectDAO subjectDAO;
    
    @Override
    public void init() throws ServletException {
        studentScoreDAO = new StudentScoreDAO();
        studentDAO = new StudentDAO();
        subjectDAO = new SubjectDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        if ("add".equals(action)) {
            request.getRequestDispatcher("/add-score.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            StudentScore studentScore = studentScoreDAO.findById(id);
            request.setAttribute("studentScore", studentScore);
            request.getRequestDispatcher("/edit-score.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            Integer studentId = Integer.parseInt(request.getParameter("studentId"));
            Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
            BigDecimal score1 = new BigDecimal(request.getParameter("score1"));
            BigDecimal score2 = new BigDecimal(request.getParameter("score2"));
            
            Student student = studentDAO.findById(studentId);
            Subject subject = subjectDAO.findById(subjectId);
            
            if (student != null && subject != null) {
                StudentScore studentScore = new StudentScore(student, subject, score1, score2);
                boolean success = studentScoreDAO.insert(studentScore);
                
                if (success) {
                    response.sendRedirect("index.jsp?message=Score added successfully");
                } else {
                    response.sendRedirect("add-score.jsp?error=Failed to add score");
                }
            } else {
                response.sendRedirect("add-score.jsp?error=Invalid student or subject");
            }
        } else if ("edit".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer studentId = Integer.parseInt(request.getParameter("studentId"));
            Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
            BigDecimal score1 = new BigDecimal(request.getParameter("score1"));
            BigDecimal score2 = new BigDecimal(request.getParameter("score2"));
            
            StudentScore studentScore = studentScoreDAO.findById(id);
            if (studentScore != null) {
                Student student = studentDAO.findById(studentId);
                Subject subject = subjectDAO.findById(subjectId);
                
                if (student != null && subject != null) {
                    studentScore.setStudent(student);
                    studentScore.setSubject(subject);
                    studentScore.setScore1(score1);
                    studentScore.setScore2(score2);
                    
                    boolean success = studentScoreDAO.update(studentScore);
                    
                    if (success) {
                        response.sendRedirect("index.jsp?message=Score updated successfully");
                    } else {
                        response.sendRedirect("edit-score.jsp?id=" + id + "&error=Failed to update score");
                    }
                }
            }
        }
    }
}

