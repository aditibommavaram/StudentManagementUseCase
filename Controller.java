package com.src.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.dto.Address;
import com.src.dto.Grade;
import com.src.dto.Marks;
import com.src.dto.Student;
import com.src.dto.Subject;
import com.src.services.StudentServices;
import com.src.services.StudentServicesInterface;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		StudentServicesInterface ssi = new StudentServices();
		if (action.equals("AddStudents")) {
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			int grade = Integer.parseInt(request.getParameter("grade"));
			String dobString = request.getParameter("dob");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dob = LocalDate.parse(dobString, formatter);
			char gender = request.getParameter("gender").charAt(0);
			String bloodGroup = request.getParameter("bloodGroup");
			long mobile = Long.parseLong(request.getParameter("mobile"));
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			Address a = new Address(address, country, state, city);
			Grade g = new Grade(grade);
			String rollno = ssi.generaterollNo(grade);
			Student s = new Student(rollno, firstName, lastName, dob, email, mobile, gender, bloodGroup, a, g);
			System.out.println(s);

			int res = ssi.addStudent(s);

			if (res > 0) {
				request.getRequestDispatcher("home.jsp").include(request, response);
			}
		}
		if (action.equals("viewstudents")) {
			List<Student> students = ssi.viewStudents();
			if (students != null && !students.isEmpty()) {
				request.setAttribute("students", students);
			}
			request.getRequestDispatcher("view.jsp").include(request, response);
		}

		if (action.equals("edit")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			int grade = Integer.parseInt(request.getParameter("grade"));
			String dobString = request.getParameter("dob");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dob = LocalDate.parse(dobString, formatter);
			char gender = request.getParameter("gender").charAt(0);
			String bloodGroup = request.getParameter("bloodGroup");
			long mobile = Long.parseLong(request.getParameter("mobile"));
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			Address a = new Address(address, country, state, city);
			Grade g = new Grade(grade, 0);
			String rollNumber = request.getParameter("rollno");
			Student student = new Student(rollNumber, firstName, lastName, dob, email, mobile, gender, bloodGroup, a,
					g);

			int m = Integer.parseInt(request.getParameter("marks"));
			String sub = request.getParameter("subject");
			Subject subject = new Subject(null, sub);
			Marks marks = new Marks(m, subject);
			int resOfSubject = ssi.addSubject(subject);
			int res = ssi.updateStudent(student, marks);
			if (res > 0) {
				request.getRequestDispatcher("home.jsp").forward(request, response);

			} else {
				request.getRequestDispatcher("home.jsp").forward(request, response);

			}
		}

		if (action.equals("viewStudent")) {
			String rollno = request.getParameter("choose");
			System.out.println(rollno);
			Student student = new Student(rollno);
			System.out.println(student);
			Student res = ssi.viewStudent(student);
			System.out.println(res + "hii");//
			if (res != null) {
				request.setAttribute("student", res);
				request.getRequestDispatcher("edit.jsp").forward(request, response);

			} else {
				request.getRequestDispatcher("edit.jsp").forward(request, response);

			}
		}

		if (action.equals("delete")) {
			String rollNo = request.getParameter("choose");
			System.out.println(rollNo);
			Student s = new Student(rollNo);
			int res = ssi.deleteStudent(s);
			System.out.println(res);
			if (res == 0) {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}

		if (action.equals("viewmarks")) {
			String rollNo = request.getParameter("rollNo");
			Student s = new Student(rollNo);
			List<Marks> marksList = ssi.viewMarks(rollNo);
			if (marksList != null) {
				request.setAttribute("marksList", marksList);
			}
			request.getRequestDispatcher("viewMarks.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}