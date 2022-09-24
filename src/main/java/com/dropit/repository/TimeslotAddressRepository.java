package com.dropit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dropit.entities.TimeslotAddressEntity;

@Repository
public interface TimeslotAddressRepository extends JpaRepository<TimeslotAddressEntity, Long> {
	
	@Query(value = "SELECT DISTINCT timeslotId FROM TimeslotAddressEntity ta WHERE ta.supportedDeliveryState = ?1")
	public List<Long> getTimeslotsByState(String state);
	
	public List<TimeslotAddressEntity> findByTimeslotId(long timeslotId);
}
