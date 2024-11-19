<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f9f9f9;
}

.container {
	max-width: 600px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

h1 {
	text-align: center;
	color: #333;
}

form label {
	display: block;
	margin-top: 15px;
	font-weight: bold;
	color: #555;
}

form input, form select, form textarea {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 14px;
}

form input[type="radio"] {
	width: auto;
}

form textarea {
	resize: vertical;
}

.radio-group label {
	display: inline-block;
	margin-right: 10px;
}

.form-buttons {
	text-align: center;
	margin-top: 20px;
}

form input[type="submit"] {
	background: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 4px;
	transition: background 0.3s ease;
}

form input[type="submit"]:hover {
	background: #45a049;
}
</style>
<script>
    function updateStates() {
        const countryStates = {
            "India": ["Telangana", "Andhra Pradesh", "Maharashtra", "Karnataka", "Tamil Nadu", "Kerala"],
            "USA": ["California", "New York", "Texas", "Florida", "Massachusetts"],
            "UK": ["England", "Scotland", "Wales", "Northern Ireland"],
            "Australia": ["New South Wales", "Queensland", "Victoria", "Tasmania"]
        };
        const countrySelect = document.getElementById("country");
        const stateSelect = document.getElementById("state");

        const selectedCountry = countrySelect.value;
        const states = countryStates[selectedCountry] || [];

        stateSelect.innerHTML = ""; 
        states.forEach(state => {
            const option = document.createElement("option");
            option.value = state;
            option.textContent = state;
            stateSelect.appendChild(option);
        });
    }
</script>
</head>
<body>
	<div class="container">
		<h1>Student Registration</h1>
		<form action="./controller?action=AddStudents" method="post">
			<label for="firstname">Enter First Name:</label> <input type="text"
				name="firstname" id="firstname" required /> <label for="lastname">Enter
				Last Name:</label> <input type="text" name="lastname" id="lastname" required />

			<label for="grade">Grade:</label> <select name="grade" id="grade">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select> <label for="dob">Enter Date Of Birth:</label> <input type="date"
				name="dob" id="dob" required /> <label for="email">Email:</label> <input
				type="text" name="email" id="email" /> <label for="gender">Gender:</label>
			<div class="radio-group">
				<label for="male">Male</label> <input type="radio" name="gender"
					id="Male" required value="male" /> <label for="female">Female</label>
				<input type="radio" name="gender" id="female" required
					value="female" />
			</div>

			<label for="bloodGroup">Blood Group:</label> <select
				name="bloodGroup" id="bloodGroup">
				<option value="O+">O+ve</option>
				<option value="O-">O-ve</option>
				<option value="A+">A+ve</option>
				<option value="A-">A-ve</option>
				<option value="AB+">AB+ve</option>
				<option value="AB-">AB-ve</option>
				<option value="B+">B+ve</option>
				<option value="B-">B-ve</option>
			</select> <label for="mobile">Enter Mobile Number:</label> <input type="tel"
				name="mobile" id="mobile" required /> <label for="address">Enter
				Address:</label>
			<textarea name="address" id="address"></textarea>

			<label for="country">Country:</label> <select name="country"
				id="country" onchange="updateStates()" required>
				<option value="">Select Country</option>
				<option value="India">India</option>
				<option value="USA">USA</option>
				<option value="UK">UK</option>
				<option value="Australia">Australia</option>
			</select> <label for="state">State:</label> <select name="state" id="state"
				required>
				<option value="">Select State</option>
			</select> <label for="city">City:</label> <input type="text" name="city"
				id="city" />

			<div class="form-buttons">
				<input type="submit" value="Submit" />
			</div>
		</form>
	</div>
</body>
</html>
