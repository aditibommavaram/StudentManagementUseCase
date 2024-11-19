<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.form {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 300px;
}

h2 {
	text-align: center;
	color: #333;
	font-size: 24px;
	margin-bottom: 20px;
}

label {
	font-size: 16px;
	color: #555;
	margin-bottom: 8px;
	display: block;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 12px;
	margin: 8px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 16px;
}

input[type="submit"] {
	width: 100%;
	padding: 14px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

.form a {
	text-decoration: none;
	color: #4CAF50;
	font-size: 14px;
	text-align: center;
	display: block;
	margin-top: 10px;
}
</style>
</head>
<body>
	<form action="home.jsp">
		<div class="form">
			<h2>LOGIN PAGE</h2>
			<label for="username">Give your Username:</label> <input type="text"
				id="username" name="username" /><br> <label for="password">Give
				your password:</label> <input type="password" id="password" name="password" /><br>
			<input type="submit" name="Submit" />
		</div>
	</form>
</body>
</html>
