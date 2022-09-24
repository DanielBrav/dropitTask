package com.dropit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropit.entities.TimeslotAddressEntity;
import com.dropit.repository.TimeslotAddressRepository;

@Service
public class TimeslotAddressService {

	@Autowired
	private TimeslotAddressRepository timeslotAddressRepository;
	
	public TimeslotAddressEntity save(TimeslotAddressEntity entity) {
		return timeslotAddressRepository.save(entity);
	}
	
	public List<Long> getTimeslotsByState(String state) {
		return timeslotAddressRepository.getTimeslotsByState(state);
	}
	
	public List<TimeslotAddressEntity> findByTimeslotId(long timeslotId) {
		return timeslotAddressRepository.findByTimeslotId(timeslotId);
	}
}
