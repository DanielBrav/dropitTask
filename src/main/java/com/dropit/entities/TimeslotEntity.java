package com.dropit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dropit.data.TimeslotData;

@Entity
@Table(name="timeslots")
public class TimeslotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeslots_id_seq")
	@SequenceGenerator(name = "timeslots_id_seq", sequenceName = "timeslots_id_seq", initialValue = 1, allocationSize = 1)
	private long id;
	
	private long startTime;
	private long endTime;
	private int bookedDeliveriesAmount;
	
	public TimeslotEntity() {}

	public TimeslotEntity(TimeslotData data) {
		this.startTime = data.getStartTime();
		this.endTime = data.getEndTime();
		this.bookedDeliveriesAmount = 0;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getBookedDeliveriesAmount() {
		return bookedDeliveriesAmount;
	}

	public void setBookedDeliveriesAmount(int bookedDeliveriesAmount) {
		this.bookedDeliveriesAmount = bookedDeliveriesAmount;
	}
}
