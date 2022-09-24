package com.dropit.data;

import java.util.ArrayList;
import java.util.List;

public class TimeslotData {
	
	private long startTime;
	private long endTime;
	private List<String> supportedDeliveryStates;
	
	public TimeslotData(long startTime, long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.supportedDeliveryStates = new ArrayList<>();
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

	public List<String> getSupportedDeliveryStates() {
		return supportedDeliveryStates;
	}

	public void setSupportedDeliveryStates(List<String> supportedDeliveryStates) {
		this.supportedDeliveryStates = supportedDeliveryStates;
	}
	
}
