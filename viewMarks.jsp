<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.src.dto.Marks"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
List<Marks> marks = (List<Marks>) request.getAttribute("marksList");
if (marks != null) {
    System.out.println("get lost");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Marks</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

h1 {
	color: #333;
	text-align: center;
	font-size: 36px;
	margin-bottom: 20px;
}

table {
	width: 90%;
	max-width: 1200px;
	border-collapse: collapse;
	margin: 20px auto;
	background-color: #fff;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 15px;
	text-align: left;
	border: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
	font-size: 18px;
}

td {
	font-size: 16px;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

p {
	font-size: 18px;
	color: #555;
	text-align: center;
	margin-top: 20px;
}

.container {
	width: 90%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>

	<div class="container">
		<h1>Marks</h1>

		<c:if test="${not empty marksList}">
			<table>
				<tr>
					<th>Marks</th>
					<th>Subject Code</th>
					<th>Subject</th>
				</tr>
				<c:forEach var="marks" items="${marksList}">
					<tr>
						<td>${marks.marks}</td>
						<td>${marks.subject.code}</td>
						<td>${marks.subject.name}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${empty marksList}">
			<p>No marks found.</p>
		</c:if>
	</div>

</body>
</html>
