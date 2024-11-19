package com.src.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.src.dto.Address;
import com.src.dto.Grade;
import com.src.dto.Marks;
import com.src.dto.Student;
import com.src.dto.Subject;

public class StudentdaoImplementation implements Studentdao {

	public Connection getMyConnection() {
		final String DB_URL = "jdbc:mysql://localhost:3306/usecasedb";
		final String USER = "root";
		final String PASSWORD = "Sranakad@21222609";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addStudent(Student student) {
		System.out.println("hi");
		int res = 0;
		int fk_grade = 0;
		int fk_address = 0;
		try (Connection con = getMyConnection()) {
			Grade g = student.getGrade();

			String sqlCheckGrade = "SELECT pk FROM grade WHERE grade = ?";
			try (PreparedStatement pstmt = con.prepareStatement(sqlCheckGrade)) {
				pstmt.setInt(1, g.getGrade());
				try (ResultSet result = pstmt.executeQuery()) {
					if (result.next()) {
						fk_grade = result.getInt("pk");
					}
				}
			}

			if (fk_grade == 0) {
				String sqlInsertGrade = "INSERT INTO grade(grade,noOfSubjects,noOfStudents) VALUES(?,?,?)";
				try (PreparedStatement pstmt = con.prepareStatement(sqlInsertGrade, Statement.RETURN_GENERATED_KEYS)) {
					pstmt.setInt(1, g.getGrade());
					pstmt.setInt(2, g.getNumberOfSubjects());
					pstmt.setInt(3, 0);
					res = pstmt.executeUpdate();
					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							fk_grade = generatedKeys.getInt(1);
						}
					}

				}
			}

			Address a = student.getAddress();
			String sql1 = "INSERT INTO address(address, country, state, city) VALUES(?, ?, ?, ?)";
			try (PreparedStatement pstmt = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, a.getAddress());
				pstmt.setString(2, a.getCountry());
				pstmt.setString(3, a.getState());
				pstmt.setString(4, a.getCity());
				res = pstmt.executeUpdate();
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						fk_address = generatedKeys.getInt(1);
					}
				}
			}
			String sqlInsertStudent = "INSERT INTO student(rollNo, firstName, lastName, dateOfBirth, email, mobile, gender, "
					+ "bloodGroup, lastModified, fk_grade,fk_address) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			try (PreparedStatement pstmt = con.prepareStatement(sqlInsertStudent)) {
				pstmt.setString(1, student.getRollNumber());
				pstmt.setString(2, student.getFirstName());
				pstmt.setString(3, student.getLastName());
				pstmt.setDate(4, Date.valueOf(student.getDateOfBirth()));
				pstmt.setString(5, student.getEmail());
				pstmt.setLong(6, student.getMobileNumber());
				pstmt.setString(7, String.valueOf(student.getGender()));
				pstmt.setString(8, student.getBloodGroup());
				pstmt.setDate(9, Date.valueOf(LocalDate.now()));
				pstmt.setInt(10, fk_grade);
				pstmt.setInt(11, fk_address);
				res = pstmt.executeUpdate();
			}
			String sqlUpdateGrade = "UPDATE grade SET noOfStudents = noOfStudents + 1 WHERE pk = ?";
			try (PreparedStatement pstmt = con.prepareStatement(sqlUpdateGrade)) {
				pstmt.setInt(1, fk_grade);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<Student> viewStudents() {
		List<Student> studentList = new ArrayList<>();
		String sql = "SELECT * FROM student";

		try (Connection con = getMyConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String rollNo = rs.getString("rollNo");
				String firstname = rs.getString("firstName");
				String lastname = rs.getString("lastName");
				Date dob = rs.getDate("dateOfBirth");
				LocalDate dateOfBirth = dob.toLocalDate();
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				char gender = rs.getString("gender").charAt(0);
				String bloodGroup = rs.getString("bloodGroup");
				int fk_address = rs.getInt("fk_address");
				int fk_grade = rs.getInt("fk_grade");

				Address address = null;
				String sql1 = "SELECT * FROM address WHERE pk = ?";
				try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
					pstmt1.setInt(1, fk_address);
					try (ResultSet rs1 = pstmt1.executeQuery()) {
						if (rs1.next()) {
							String addr = rs1.getString("address");
							String country = rs1.getString("country");
							String state = rs1.getString("state");
							String city = rs1.getString("city");
							address = new Address(addr, country, state, city);
						}
					}
				}

				Grade grade = null;
				String sql2 = "SELECT * FROM grade WHERE pk = ?";
				try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
					pstmt2.setInt(1, fk_grade);
					try (ResultSet rs2 = pstmt2.executeQuery()) {
						if (rs2.next()) {
							int gradeValue = rs2.getInt("grade");
							int noOfSubjects = rs2.getInt("noOfSubjects");
							grade = new Grade(gradeValue, noOfSubjects);
						}
					}
				}

				Student student = new Student(rollNo, firstname, lastname, dateOfBirth, email, mobile, gender,
						bloodGroup, address, grade);
				System.out.println(student);
				System.out.println();
				studentList.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;
	}

	public int getnoOfStudents(int g) {
		int value = 0;
		String query = "Select noOfStudents from grade where grade=?";
		ResultSet rs = null;
		try (Connection con = getMyConnection(); PreparedStatement psmt = con.prepareStatement(query)) {
			psmt.setInt(1, g);
			rs = psmt.executeQuery();
			if (rs.next()) {

				value = rs.getInt("noOfStudents");
				System.out.println(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public Student viewStudent(Student student) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student where rollNo=?";
		Student s = null;
		try (Connection con = getMyConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, student.getRollNumber());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String rollNo = rs.getString("rollNo");
				String firstname = rs.getString("firstName");
				String lastname = rs.getString("lastName");
				Date dob = rs.getDate("dateOfBirth");
				LocalDate dateOfBirth = dob.toLocalDate();
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				char gender = rs.getString("gender").charAt(0);
				String bloodGroup = rs.getString("bloodGroup");
				int fk_address = rs.getInt("fk_address");
				int fk_grade = rs.getInt("fk_grade");

				Address address = null;
				String sql1 = "SELECT * FROM address WHERE pk = ?";
				try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
					pstmt1.setInt(1, fk_address);
					try (ResultSet rs1 = pstmt1.executeQuery()) {
						if (rs1.next()) {
							String addr = rs1.getString("address");
							String country = rs1.getString("country");
							String state = rs1.getString("state");
							String city = rs1.getString("city");
							address = new Address(addr, country, state, city);
						}
					}
				}

				Grade grade = null;
				String sql2 = "SELECT * FROM grade WHERE pk = ?";
				try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
					pstmt2.setInt(1, fk_grade);
					try (ResultSet rs2 = pstmt2.executeQuery()) {
						if (rs2.next()) {
							int gradeValue = rs2.getInt("grade");
							int noOfSubjects = rs2.getInt("noOfSubjects");
							grade = new Grade(gradeValue, noOfSubjects);
						}
					}
				}

				s = new Student(rollNo, firstname, lastname, dateOfBirth, email, mobile, gender, bloodGroup, address,
						grade);
				System.out.println("in view inside");
				System.out.println(s.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("in view outside");
		return s;
	}

	@Override
	public int deleteStudent(Student student) {
		String sqlSelect = "SELECT pk, fk_address, fk_grade FROM student WHERE rollNo = ?";
		String sqlDeleteMarks = "DELETE FROM marks WHERE fk_student = ?";
		String sqlDeleteAddress = "DELETE FROM address WHERE pk = ?";
		String sqlDeleteGrade = "DELETE FROM grade WHERE pk = ?";
		String sqlDeleteStudent = "DELETE FROM student WHERE rollNo = ?";

		try (Connection con = getMyConnection()) {
			int studentPk = 0, fkAddress = 0, fkGrade = 0;

			try (PreparedStatement pstmtSelect = con.prepareStatement(sqlSelect)) {
				pstmtSelect.setString(1, student.getRollNumber());
				try (ResultSet rs = pstmtSelect.executeQuery()) {
					if (rs.next()) {
						studentPk = rs.getInt("pk");
						fkAddress = rs.getInt("fk_address");
						fkGrade = rs.getInt("fk_grade");
					} else {
						return 0;
					}
				}
			}

			try (PreparedStatement pstmtDeleteMarks = con.prepareStatement(sqlDeleteMarks)) {
				pstmtDeleteMarks.setInt(1, studentPk);
				pstmtDeleteMarks.executeUpdate();
			}

			try (PreparedStatement pstmtDeleteAddress = con.prepareStatement(sqlDeleteAddress)) {
				pstmtDeleteAddress.setInt(1, fkAddress);
				pstmtDeleteAddress.executeUpdate();
			}

			try (PreparedStatement pstmtDeleteGrade = con.prepareStatement(sqlDeleteGrade)) {
				pstmtDeleteGrade.setInt(1, fkGrade);
				pstmtDeleteGrade.executeUpdate();
			}

			try (PreparedStatement pstmtDeleteStudent = con.prepareStatement(sqlDeleteStudent)) {
				pstmtDeleteStudent.setString(1, student.getRollNumber());
				int res = pstmtDeleteStudent.executeUpdate();
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateStudent(Student student, Marks marks) {
		// TODO Auto-generated method stub
		int res = 0;
		String selectQuery = "SELECT * FROM student WHERE rollNo = ?";
		String studentQuery = "UPDATE student SET rollNo = ?, firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, bloodGroup = ?, mobile = ?, email = ? WHERE pk = ?";
		String addressQuery = "UPDATE address SET address = ?, country = ?, state = ?, city = ? WHERE pk = ?";
		String gradeQuery = "UPDATE grade SET grade = ? WHERE pk = ?";
		String checkMarksQuery = "SELECT * FROM marks WHERE fk_student = ? AND fk_subject = ?";
		String sqlCheckSubject = "SELECT pk FROM subject WHERE name = ?";

		try (Connection connection = getMyConnection()) {
			int fk_address = 0, fk_grade = 0, pk = 0, fk_subject = 0;

			try (PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {
				pstmt.setString(1, student.getRollNumber());
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						pk = rs.getInt("pk");
						fk_address = rs.getInt("fk_address");
						fk_grade = rs.getInt("fk_grade");
					} else {
						return 0;
					}
				}
			}

			try (PreparedStatement studentPstmt = connection.prepareStatement(studentQuery)) {
				studentPstmt.setString(1, student.getRollNumber());
				studentPstmt.setString(2, student.getFirstName());
				studentPstmt.setString(3, student.getLastName());
				studentPstmt.setDate(4, Date.valueOf(student.getDateOfBirth()));
				studentPstmt.setString(5, String.valueOf(student.getGender()));
				studentPstmt.setString(6, student.getBloodGroup());
				studentPstmt.setLong(7, student.getMobileNumber());
				studentPstmt.setString(8, student.getEmail());
				studentPstmt.setInt(9, pk);
				res += studentPstmt.executeUpdate();
			}

			try (PreparedStatement addressPstmt = connection.prepareStatement(addressQuery)) {
				Address address = student.getAddress();
				addressPstmt.setString(1, address.getAddress());
				addressPstmt.setString(2, address.getCountry());
				addressPstmt.setString(3, address.getState());
				addressPstmt.setString(4, address.getCity());
				addressPstmt.setInt(5, fk_address);
				res += addressPstmt.executeUpdate();
			}

			try (PreparedStatement gradePstmt = connection.prepareStatement(gradeQuery)) {
				Grade grade = student.getGrade();
				gradePstmt.setInt(1, grade.getGrade());
				gradePstmt.setInt(2, fk_grade);
				res += gradePstmt.executeUpdate();
			}

			try (PreparedStatement pstmtCheck = connection.prepareStatement(sqlCheckSubject)) {
				pstmtCheck.setString(1, marks.getSubject().getName());
				try (ResultSet result = pstmtCheck.executeQuery()) {
					if (result.next()) {
						fk_subject = result.getInt("pk");
					}
				}
			}

			try (PreparedStatement pstmtCheck = connection.prepareStatement(checkMarksQuery)) {
				pstmtCheck.setInt(1, pk);
				pstmtCheck.setInt(2, fk_subject);
				try (ResultSet result = pstmtCheck.executeQuery()) {
					if (result.next()) {
						String sqlUpdate = "UPDATE marks SET marks = ? WHERE fk_student = ? AND fk_subject = ?";
						try (PreparedStatement pstmtUpdate = connection.prepareStatement(sqlUpdate)) {
							pstmtUpdate.setInt(1, marks.getMarks());
							pstmtUpdate.setInt(2, pk);
							pstmtUpdate.setInt(3, fk_subject);
							res += pstmtUpdate.executeUpdate();
						}
					} else {
						String sqlInsert = "INSERT INTO marks(fk_student, marks, fk_subject) VALUES(?, ?, ?)";
						try (PreparedStatement pstmtInsert = connection.prepareStatement(sqlInsert)) {
							pstmtInsert.setInt(1, pk);
							pstmtInsert.setInt(2, marks.getMarks());
							pstmtInsert.setInt(3, fk_subject);
							res += pstmtInsert.executeUpdate();
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int addSubject(Subject subject) {
		int res = 0;

		try (Connection con = getMyConnection()) {
			String sqlCheckSubject = "SELECT pk FROM subject WHERE name = ?";
			try (PreparedStatement pstmtCheck = con.prepareStatement(sqlCheckSubject)) {
				pstmtCheck.setString(1, subject.getName());

				try (ResultSet result = pstmtCheck.executeQuery()) {
					if (result.next()) {
						return 0;
					}
				}
			}

			String sqlInsertSubject = "INSERT INTO subject(name, code) VALUES(?, ?)";
			try (PreparedStatement pstmtInsert = con.prepareStatement(sqlInsertSubject)) {
				pstmtInsert.setString(1, subject.getName());
				pstmtInsert.setString(2, subject.getCode());
				res = pstmtInsert.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<Marks> viewMarks(String rollNo) {
		List<Marks> marksList = new ArrayList<>();
		String sqlStudent = "SELECT pk FROM student WHERE rollNo = ?";
		String sqlMarks = "SELECT marks, fk_subject FROM marks WHERE fk_student = ?";
		String sqlSubject = "SELECT code, name FROM subject WHERE pk = ?";

		try (Connection con = getMyConnection();
				PreparedStatement pstmtStudent = con.prepareStatement(sqlStudent);
				PreparedStatement pstmtMarks = con.prepareStatement(sqlMarks);
				PreparedStatement pstmtSubject = con.prepareStatement(sqlSubject)) {

			pstmtStudent.setString(1, rollNo);
			ResultSet rsStudent = pstmtStudent.executeQuery();

			int studentPk = 0;
			if (rsStudent.next()) {
				studentPk = rsStudent.getInt("pk");
			}

			if (studentPk == 0) {
				return marksList;
			}

			pstmtMarks.setInt(1, studentPk);
			ResultSet rsMarks = pstmtMarks.executeQuery();

			while (rsMarks.next()) {
				int marks = rsMarks.getInt("marks");
				int subjectPk = rsMarks.getInt("fk_subject");

				pstmtSubject.setInt(1, subjectPk);
				ResultSet rsSubject = pstmtSubject.executeQuery();

				if (rsSubject.next()) {
					String code = rsSubject.getString("code");
					String name = rsSubject.getString("name");

					Subject subject = new Subject(code, name);
					Marks mark = new Marks(marks, subject);
					System.out.println(mark);
					marksList.add(mark);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return marksList;
	}
}
