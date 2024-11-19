package com.src.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", mobileNumber=" + mobileNumber + ", gender="
				+ gender + ", bloodGroup=" + bloodGroup + ", address=" + address + ", grade=" + grade + "]";
	}

	public Student(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	private String rollNumber;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String email;
	private Long mobileNumber;
	private char gender;
	private String bloodGroup;
	private Address address;
	private Grade grade;

	public Student(String rollNumber, String firstName, String lastName, LocalDate dateOfBirth, String email,
			Long mobileNumber, char gender, String bloodGroup, Address address, Grade grade) {
		this.rollNumber = rollNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.grade = grade;
	}

	public Student() {

	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
