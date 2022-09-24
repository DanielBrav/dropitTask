package com.dropit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropit.entities.TimeslotEntity;
import com.dropit.repository.TimeslotRepository;

@Service
public class TimeslotService {
	
	@Autowired
	private TimeslotRepository timeslotRepository;
	
	public TimeslotEntity save(TimeslotEntity timeslot) {
		return timeslotRepository.save(timeslot);
	}
	
	public TimeslotEntity findById(Long id) {
		return timeslotRepository.findById(id).get();
	}
	
	public List<TimeslotEntity> saveAll(List<TimeslotEntity> timeslots) {
		return timeslotRepository.saveAll(timeslots);
	}
}
