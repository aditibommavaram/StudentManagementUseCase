package com.src.dto;

public class Address {

	@Override
	public String toString() {
		return "Address [address=" + address + ", country=" + country + ", state=" + state + ", city=" + city + "]";
	}

	private String address;
	private String country;
	private String state;
	private String city;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Address(String address, String country, String state, String city) {
		this.address = address;
		this.country = country;
		this.state = state;
		this.city = city;
	}

	public Address() {

	}
}
