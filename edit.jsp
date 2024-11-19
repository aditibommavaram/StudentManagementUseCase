<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.src.dto.Student"%>
<%
Student s = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 50%;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
}

h1 {
	text-align: center;
	color: #333;
}

form label {
	display: block;
	margin: 10px 0 5px;
	color: #555;
}

form input, form select, form textarea {
	width: 100%;
	padding: 8px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
}

form input[type="radio"] {
	width: auto;
	margin-right: 5px;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #4CAF50;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Edit Student</h1>
		<form action="./controller?action=edit" method="post">
			<label for="rollno">Roll No:</label> <input type="text" name="rollno"
				id="rollno" required value="<%= s.getRollNumber() %>"
				readonly="readonly" /> <label for="firstname">Enter First
				Name:</label> <input type="text" name="firstName" id="firstName" required
				value="<%= s.getFirstName() %>" /> <label for="lastname">Enter
				Last Name:</label> <input type="text" name="lastName" id="lastName" required
				value="<%= s.getLastName() %>" /> <label for="grade">Enter
				Grade:</label> <select name="grade">
				<% for (int i = 1; i <= 10; i++) { %>
				<option value="<%= i %>"
					<%= (i == s.getGrade().getGrade() ? "selected" : "") %>><%= i %></option>
				<% } %>
			</select> <label for="dob">Enter Date of Birth:</label> <input type="date"
				name="dob" id="dob" required value="<%= s.getDateOfBirth() %>" /> <label
				for="gender">Enter Gender:</label> <label for="male">Male</label> <input
				type="radio" name="gender" id="male" value="m"
				<%= (s.getGender() == 'm' ? "checked" : "") %> /> <label
				for="female">Female</label> <input type="radio" name="gender"
				id="female" value="f" <%= (s.getGender() == 'f' ? "checked" : "") %> />

			<label for="bloodGroup">Enter Blood Group:</label> <input type="text"
				name="bloodGroup" id="bloodGroup" required
				value="<%= s.getBloodGroup() %>" /> <label for="mobile">Enter
				Mobile:</label> <input type="tel" name="mobile" id="mobile" required
				value="<%= s.getMobileNumber() %>" /> <label for="email">Enter
				Email:</label> <input type="email" name="email" id="email" required
				value="<%= s.getEmail() %>" /> <label for="address">Enter
				Address:</label>
			<textarea name="address" id="address"><%= s.getAddress().getAddress() %></textarea>

			<label for="country">Enter Country:</label> <input type="text"
				name="country" id="country" required
				value="<%= s.getAddress().getCountry() %>" /> <label for="state">Enter
				State:</label> <input type="text" name="state" id="state" required
				value="<%= s.getAddress().getState() %>" /> <label for="city">Enter
				City:</label> <input type="text" name="city" id="city" required
				value="<%= s.getAddress().getCity() %>" /> <label>Enter
				Marks:</label> <input type="text" name="marks" /> <label>Choose
				Subject:</label> <select name="subject">
				<option value="science">Science</option>
				<option value="social">Social</option>
				<option value="math">Math</option>
				<option value="english">English</option>
				<option value="french">French</option>
			</select>

			<button type="submit">Edit Student</button>
		</form>
	</div>
</body>
</html>
