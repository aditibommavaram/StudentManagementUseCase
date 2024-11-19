package com.src.dao;

import java.util.List;

import com.src.dto.Marks;
import com.src.dto.Student;
import com.src.dto.Subject;

public interface Studentdao {

	public List<Student> viewStudents();

	public int getnoOfStudents(int g);

	public int addStudent(Student student);

	public Student viewStudent(Student student);

	public int updateStudent(Student student, Marks marks);

	public int deleteStudent(Student student);

	public int addSubject(Subject subject);

	public List<Marks> viewMarks(String rollNo);
}
