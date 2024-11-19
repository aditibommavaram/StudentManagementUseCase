package com.src.dto;

public class Grade {

	@Override
	public String toString() {
		return "Grade [grade=" + grade + ", numberOfSubjects=" + numberOfSubjects + "]";
	}

	private int grade;
	private int numberOfSubjects;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getNumberOfSubjects() {
		return numberOfSubjects;
	}

	public void setNumberOfSubjects(int numberOfSubjects) {
		this.numberOfSubjects = numberOfSubjects;
	}

	public Grade(int grade) {
		this.grade = grade;
//	this.numberOfSubjects = numberOfSubjects;
	}

	public Grade(int grade, int numberOfSubjects) {
		this.grade = grade;
		this.numberOfSubjects = numberOfSubjects;
	}

	public Grade() {

	}

}
