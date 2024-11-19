package com.src.services;

import java.util.List;

import com.src.dao.Studentdao;
import com.src.dao.StudentdaoImplementation;
import com.src.dto.Marks;
import com.src.dto.Student;
import com.src.dto.Subject;

public class StudentServices implements StudentServicesInterface {

	Studentdao sdao = new StudentdaoImplementation();

	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		if (student.getGrade().getGrade() <= 5) {
			student.getGrade().setNumberOfSubjects(5);
		} else {
			student.getGrade().setNumberOfSubjects(10);
		}

		return sdao.addStudent(student);
	}

	@Override
	public List<Student> viewStudents() {
		// TODO Auto-generated method stub
		return sdao.viewStudents();
	}

	@Override
	public String generaterollNo(int g) {
		// TODO Auto-generated method stub
		int value = sdao.getnoOfStudents(g);
		String str = String.valueOf(value);
		String gradestr = String.valueOf(g);
		String rollNo = gradestr + "0" + str;
		return rollNo;
	}

	@Override
	public Student viewStudent(Student student) {
		// TODO Auto-generated method stub
		return sdao.viewStudent(student);
	}

	@Override
	public int updateStudent(Student student, Marks marks) {
		// TODO Auto-generated method stub
		return sdao.updateStudent(student, marks);
	}

	@Override
	public int deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return sdao.deleteStudent(student);
	}

	@Override
	public int addSubject(Subject subject) {
		// TODO Auto-generated method stub
		if (subject.getName().equals("science")) {
			subject.setCode("sci");
		} else if (subject.getName().equals("social")) {
			subject.setCode("soc");
		} else if (subject.getName().equals("math")) {
			subject.setCode("math");
		} else if (subject.getName().equals("english")) {
			subject.setCode("eng");
		} else if (subject.getName().equals("french")) {
			subject.setCode("fre");
		} else {
			System.out.println("bye");
		}

		return sdao.addSubject(subject);
	}

	@Override
	public List<Marks> viewMarks(String rollNo) {
		// TODO Auto-generated method stub
		return sdao.viewMarks(rollNo);
	}

}
