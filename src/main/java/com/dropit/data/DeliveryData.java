package com.dropit.data;

public class DeliveryData {
	
	private String user;
	private long timeslotId;
	
	public DeliveryData(String user, long timeslotId) {
		this.user = user;
		this.timeslotId = timeslotId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(long timeslotId) {
		this.timeslotId = timeslotId;
	}

}
