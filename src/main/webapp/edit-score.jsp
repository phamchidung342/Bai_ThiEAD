<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.fpt.aptech.sis.dao.StudentDAO" %>
<%@ page import="com.fpt.aptech.sis.dao.SubjectDAO" %>

<%
    StudentDAO studentDAO = new StudentDAO();
    SubjectDAO subjectDAO = new SubjectDAO();
    request.setAttribute("students", studentDAO.findAll());
    request.setAttribute("subjects", subjectDAO.findAll());
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Score - SIS</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
        }
        
        .header {
            background-color: #2d5016;
            color: white;
            padding: 20px 0;
            text-align: center;
        }
        
        .container {
            max-width: 600px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        
        h2 {
            color: #2d5016;
            margin-bottom: 25px;
            font-size: 24px;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }
        
        select, input[type="number"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        
        select:focus, input[type="number"]:focus {
            outline: none;
            border-color: #4a7c2a;
        }
        
        .btn-group {
            display: flex;
            gap: 15px;
            margin-top: 25px;
        }
        
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s;
        }
        
        .btn-primary {
            background-color: #4a7c2a;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #3a6320;
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        
        .message {
            padding: 12px 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        input[type="number"] {
            -moz-appearance: textfield;
        }
        
        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Student Information System</h1>
    </div>
    
    <div class="container">
        <div class="form-container">
            <h2>Edit Score</h2>
            
            <c:if test="${not empty param.error}">
                <div class="message">
                    ${param.error}
                </div>
            </c:if>
            
            <c:if test="${not empty studentScore}">
                <form action="score" method="POST">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="${studentScore.studentScoreId}">
                    
                    <div class="form-group">
                        <label for="studentId">Student *</label>
                        <select id="studentId" name="studentId" required>
                            <option value="">-- Select Student --</option>
                            <c:forEach var="student" items="${students}">
                                <option value="${student.studentId}" 
                                    ${student.studentId == studentScore.student.studentId ? 'selected' : ''}>
                                    ${student.studentCode} - ${student.fullName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="subjectId">Subject *</label>
                        <select id="subjectId" name="subjectId" required>
                            <option value="">-- Select Subject --</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectId}"
                                    ${subject.subjectId == studentScore.subject.subjectId ? 'selected' : ''}>
                                    ${subject.subjectCode} - ${subject.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="score1">Score 1 *</label>
                        <input type="number" id="score1" name="score1" step="0.01" min="0" max="10" 
                            value="${studentScore.score1}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="score2">Score 2 *</label>
                        <input type="number" id="score2" name="score2" step="0.01" min="0" max="10" 
                            value="${studentScore.score2}" required>
                    </div>
                    
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">Update Score</button>
                        <a href="index.jsp" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</body>
</html>

