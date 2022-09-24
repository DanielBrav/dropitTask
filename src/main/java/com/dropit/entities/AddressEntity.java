package com.dropit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="addresses")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_id_seq")
	@SequenceGenerator(name = "addresses_id_seq", sequenceName = "addresses_id_seq", initialValue = 1, allocationSize = 1)
	private long id;
	
	private String street;
	private String line1;
	private String line2;
	private String country;
	private String postcode;
	private String state;

	public AddressEntity() {}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
