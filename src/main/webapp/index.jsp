<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.fpt.aptech.sis.dao.StudentScoreDAO" %>
<%@ page import="com.fpt.aptech.sis.util.GradeConverter" %>
<%@ page import="java.math.BigDecimal" %>

<%
    StudentScoreDAO studentScoreDAO = new StudentScoreDAO();
    request.setAttribute("studentScores", studentScoreDAO.findAllWithDetails());
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Information System</title>
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
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .header h1 {
            font-size: 28px;
            font-weight: 600;
        }
        
        .sub-header {
            background-color: #4a7c2a;
            color: white;
            padding: 15px 0;
            text-align: center;
        }
        
        .sub-header h2 {
            font-size: 20px;
            font-weight: 500;
        }
        
        .container {
            max-width: 1400px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .action-buttons {
            margin-bottom: 20px;
            display: flex;
            gap: 15px;
        }
        
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            transition: all 0.3s;
        }
        
        .btn-primary {
            background-color: #4a7c2a;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #3a6320;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        
        .btn-edit {
            background-color: #007bff;
            color: white;
            padding: 6px 12px;
            font-size: 14px;
        }
        
        .btn-edit:hover {
            background-color: #0056b3;
        }
        
        .message {
            padding: 12px 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-weight: 500;
        }
        
        .message.success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        .table-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        thead {
            background-color: #4a7c2a;
            color: white;
        }
        
        th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            font-size: 14px;
        }
        
        td {
            padding: 12px 15px;
            border-bottom: 1px solid #e0e0e0;
        }
        
        tbody tr:hover {
            background-color: #f8f9fa;
        }
        
        tbody tr:last-child td {
            border-bottom: none;
        }
        
        .text-center {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Student Information System</h1>
    </div>
    
    <div class="sub-header">
        <h2>Student Information</h2>
    </div>
    
    <div class="container">
        <c:if test="${not empty param.message}">
            <div class="message success">
                ${param.message}
            </div>
        </c:if>
        
        <c:if test="${not empty param.error}">
            <div class="message error">
                ${param.error}
            </div>
        </c:if>
        
        <div class="action-buttons">
            <a href="student?action=add" class="btn btn-primary">
                <span>+</span> Student
            </a>
            <a href="score?action=add" class="btn btn-primary">
                <span>+</span> Score
            </a>
        </div>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Student Id</th>
                        <th>Student Name</th>
                        <th>Subject Name</th>
                        <th>Score 1</th>
                        <th>Score 2</th>
                        <th>Credit</th>
                        <th>Grade</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="score" items="${studentScores}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${score.student.studentCode}</td>
                            <td>${score.student.fullName}</td>
                            <td>${score.subject.subjectName}</td>
                            <td>${score.score1}</td>
                            <td>${score.score2}</td>
                            <td>${score.subject.credit}</td>
                            <td>
                                <strong>
                                    <%
                                        com.fpt.aptech.sis.entity.StudentScore sc = (com.fpt.aptech.sis.entity.StudentScore)pageContext.getAttribute("score");
                                        out.print(GradeConverter.convertToGrade(sc.getScore1(), sc.getScore2()));
                                    %>
                                </strong>
                            </td>
                            <td class="text-center">
                                <a href="score?action=edit&id=${score.studentScoreId}" class="btn btn-edit">✏️</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty studentScores}">
                        <tr>
                            <td colspan="9" class="text-center" style="padding: 40px; color: #999;">
                                No student information available. Please add students and scores.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

