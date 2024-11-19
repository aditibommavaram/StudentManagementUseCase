package com.src.services;

import java.util.List;

import com.src.dto.Marks;
import com.src.dto.Student;
import com.src.dto.Subject;

public interface StudentServicesInterface {

	public int addStudent(Student student);

	public List<Student> viewStudents();

	public String generaterollNo(int grade);

	public Student viewStudent(Student student);

	public int updateStudent(Student student, Marks marks);

	public int deleteStudent(Student student);

	public int addSubject(Subject subject);

	public List<Marks> viewMarks(String rollNo);
}
