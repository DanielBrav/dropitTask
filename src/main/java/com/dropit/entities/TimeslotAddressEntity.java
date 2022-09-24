package com.dropit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="timeslot_address")
public class TimeslotAddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeslot_address_id_seq")
	@SequenceGenerator(name = "timeslot_address_id_seq", sequenceName = "timeslot_address_id_seq", initialValue = 1, allocationSize = 1)
	private long id;
	
	private long timeslotId;
	private String supportedDeliveryState;

	public TimeslotAddressEntity() {}

	public long getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(long timeslotId) {
		this.timeslotId = timeslotId;
	}

	public String getSupportedDeliveryState() {
		return supportedDeliveryState;
	}

	public void setSupportedDeliveryState(String supportedDeliveryState) {
		this.supportedDeliveryState = supportedDeliveryState;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
