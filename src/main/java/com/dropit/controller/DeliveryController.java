package com.dropit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dropit.data.DeliveryData;
import com.dropit.entities.DeliveryEntity;
import com.dropit.service.DeliveryService;

@Controller
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private TimeslotController timeslotController;

	Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	/**
	 * 
	 * @param deliveryToAdd
	 */
	public void addDelivery(DeliveryData deliveryToAdd) {
		DeliveryEntity entity = new DeliveryEntity(deliveryToAdd);
		if(timeslotController.bookTimeslot(deliveryToAdd.getTimeslotId())) {
			deliveryService.save(entity);
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public void completeDelivery(long id) {
		DeliveryEntity entity = deliveryService.findById(id);
		entity.setCompleted(true);
		deliveryService.save(entity);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteDelivery(long id) {
		deliveryService.deleteById(id);
	}
	
}
