package com.src.dto;

public class Subject {

	@Override
	public String toString() {
		return "Subject [code=" + code + ", name=" + name + "]";
	}

	private String code;
	private String name;

	public Subject(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Subject() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}