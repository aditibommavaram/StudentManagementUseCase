<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.src.dto.Student"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    List<Student> studentList = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f4f4f9;
	color: #333;
}

h1 {
	text-align: center;
	color: #2c3e50;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

.actions {
	text-align: center;
	margin: 20px 0;
}

button {
	padding: 10px 20px;
	margin: 5px;
	font-size: 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.no-students {
	text-align: center;
	margin: 20px;
	font-size: 18px;
	color: #e74c3c;
}
</style>
</head>
<body>
	<h1>Students</h1>
	<c:if test="${not empty students}">
		<form method="post" action="./controller">
			<table>
				<tr>
					<th>Roll Number</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date of Birth</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>Gender</th>
					<th>Blood Group</th>
					<th>Address</th>
					<th>Country</th>
					<th>State</th>
					<th>City</th>
					<th>Grade</th>
					<th>Number of Subjects</th>
					<th>Select</th>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><a
							href="./controller?action=viewmarks&rollNo=${student.rollNumber}">${student.rollNumber}</a></td>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.dateOfBirth}</td>
						<td>${student.email}</td>
						<td>${student.mobileNumber}</td>
						<td>${student.gender}</td>
						<td>${student.bloodGroup}</td>
						<td>${student.address.address}</td>
						<td>${student.address.country}</td>
						<td>${student.address.state}</td>
						<td>${student.address.city}</td>
						<td>${student.grade.grade}</td>
						<td>${student.grade.numberOfSubjects}</td>
						<td><input type="radio" name="choose"
							value="${student.rollNumber}" required></td>
					</tr>
				</c:forEach>
			</table>
			<div class="actions">
				<button type="submit" name="action" value="viewStudent">Edit
					Student</button>
				<button type="submit" name="action" value="delete">Delete
					Student</button>
			</div>
		</form>
	</c:if>
	<c:if test="${empty students}">
		<p class="no-students">No students found.</p>
	</c:if>
</body>
</html>
