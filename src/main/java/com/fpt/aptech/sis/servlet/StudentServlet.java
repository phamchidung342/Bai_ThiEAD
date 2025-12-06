package com.fpt.aptech.sis.servlet;

import com.fpt.aptech.sis.dao.StudentDAO;
import com.fpt.aptech.sis.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentServlet", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {
    
    private StudentDAO studentDAO;
    
    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        switch (action) {
            case "add":
                request.getRequestDispatcher("/add-student.jsp").forward(request, response);
                break;
            case "edit":
                Integer id = Integer.parseInt(request.getParameter("id"));
                Student student = studentDAO.findById(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/edit-student.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String studentCode = request.getParameter("studentCode");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            
            Student student = new Student(studentCode, fullName, address);
            boolean success = studentDAO.insert(student);
            
            if (success) {
                response.sendRedirect("index.jsp?message=Student added successfully");
            } else {
                response.sendRedirect("add-student.jsp?error=Failed to add student");
            }
        } else if ("edit".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String studentCode = request.getParameter("studentCode");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            
            Student student = studentDAO.findById(id);
            if (student != null) {
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                boolean success = studentDAO.update(student);
                
                if (success) {
                    response.sendRedirect("index.jsp?message=Student updated successfully");
                } else {
                    response.sendRedirect("edit-student.jsp?id=" + id + "&error=Failed to update student");
                }
            }
        }
    }
}

