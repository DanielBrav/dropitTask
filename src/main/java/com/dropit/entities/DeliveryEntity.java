package com.dropit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dropit.data.DeliveryData;

@Entity
@Table(name="deliveries")
public class DeliveryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deliveries_id_seq")
	@SequenceGenerator(name = "deliveries_id_seq", sequenceName = "deliveries_id_seq", initialValue = 1, allocationSize = 1)
	private long id;
	
	private long timeslotId;
	private String userName;
	private boolean completed;
	
	public DeliveryEntity() {}
	
	public DeliveryEntity(DeliveryData deliveryData) {
		this.timeslotId = deliveryData.getTimeslotId();
		this.userName = deliveryData.getUser();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(long timeslotId) {
		this.timeslotId = timeslotId;
	}
	
	public String getUser() {
		return userName;
	}

	public void setUser(String user) {
		this.userName = user;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
