package com.dropit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropit.entities.DeliveryEntity;
import com.dropit.repository.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	public DeliveryEntity save(DeliveryEntity entity) {
		return deliveryRepository.save(entity);
	}
	
	public DeliveryEntity findById(long id) {
		return deliveryRepository.findById(id).get();
	}
	
	public void deleteById(long id) {
		deliveryRepository.deleteById(id);
	}
}
