package com.dropit.data;

public class AddressData {
	
	private String street;
	private String line1;
	private String line2;
	private String country;
	private String postcode;
	private String state;
	
	public AddressData(String street, String line1, String line2, String country, String postcode, String state) {
		this.street = street;
		this.line1 = line1;
		this.line2 = line2;
		this.country = country;
		this.postcode = postcode;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
