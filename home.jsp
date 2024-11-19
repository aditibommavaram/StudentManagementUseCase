<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

.home {
	text-align: center;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
}

.heading {
	font-size: 30px;
	margin-bottom: 20px;
	color: #333;
}

button {
	width: 200px;
	padding: 15px;
	margin: 10px;
	background-color: #4CAF50;
	color: #fff;
	font-size: 18px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #45a049;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="home">
		<h2>Planon School</h2>
		<a href="add.jsp"><button>Add</button></a> <a
			href="./controller?action=viewstudents"><button>View All
				Students</button></a>
	</div>
</body>
</html>
