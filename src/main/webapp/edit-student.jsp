<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student - SIS</title>
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
        
        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        
        input[type="text"]:focus {
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
    </style>
</head>
<body>
    <div class="header">
        <h1>Student Information System</h1>
    </div>
    
    <div class="container">
        <div class="form-container">
            <h2>Edit Student</h2>
            
            <c:if test="${not empty param.error}">
                <div class="message">
                    ${param.error}
                </div>
            </c:if>
            
            <c:if test="${not empty student}">
                <form action="student" method="POST">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="${student.studentId}">
                    
                    <div class="form-group">
                        <label for="studentCode">Student Code *</label>
                        <input type="text" id="studentCode" name="studentCode" 
                            value="${student.studentCode}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" 
                            value="${student.fullName}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address" 
                            value="${student.address}">
                    </div>
                    
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">Update Student</button>
                        <a href="index.jsp" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</body>
</html>

