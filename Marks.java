package com.src.dto;

public class Marks {

	@Override
	public String toString() {
		return "Marks [marks=" + marks + ", subject=" + subject + "]";
	}

	private int marks;
	private Subject subject;

	public Marks(int marks, Subject subject) {
		this.marks = marks;
		this.subject = subject;
	}

	public Marks() {

	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
