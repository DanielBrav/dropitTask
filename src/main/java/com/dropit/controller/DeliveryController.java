package com.dropit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dropit.data.DeliveryData;
import com.dropit.entities.DeliveryEntity;
import com.dropit.service.DeliveryService;

@Controller
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	/**
	 * 
	 * @param deliveryToAdd
	 */
	public void addDelivery(DeliveryData deliveryToAdd) {
		DeliveryEntity entity = new DeliveryEntity(deliveryToAdd);
		deliveryService.save(entity);
	}
	
	public void completeDelivery(long id) {
		DeliveryEntity entity = deliveryService.findById(id);
		entity.setCompleted(true);
		deliveryService.save(entity);
	}
	
	public void deleteDelivery(long id) {
		deliveryService.deleteById(id);
	}
	
}
