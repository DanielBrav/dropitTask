package com.dropit.data;

public class HolidayData {
	
	private long startTime;
	private long endTime;
	
	public HolidayData(long startTime) {
		this.startTime = startTime;
		this.endTime = startTime + 24*60*60*1000; // whole day
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
	
}
