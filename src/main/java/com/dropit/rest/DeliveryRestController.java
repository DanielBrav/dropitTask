package com.dropit.rest;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dropit.controller.AddressController;
import com.dropit.controller.DeliveryController;
import com.dropit.controller.TimeslotController;
import com.dropit.data.AddressData;
import com.dropit.data.DeliveryData;
import com.dropit.data.TimeslotData;

@RestController
public class DeliveryRestController {
	
	@Autowired
	private AddressController addressController;
	
	@Autowired
	private TimeslotController timeslotController;

	@Autowired
	private DeliveryController deliveryController;
	
	@PostMapping(value = "/resolve-address")
	public AddressData resolveAddress(@RequestBody String address) throws JSONException {
	    return addressController.resolveAddress(address);
	}
	
	@PostMapping(value = "/timeslots")
	public List<TimeslotData> timeslots(@RequestBody AddressData address) throws JSONException {
	    return timeslotController.retrieveAvailableTimeslots(address);
	}
	
	@PostMapping(value = "/deliveries")
	public void deliveries(@RequestBody DeliveryData delivery) throws JSONException {
		deliveryController.addDelivery(delivery);
	}
	
	@PostMapping(value = "/deliveries/{delivery_id}/complete")
	public void completeDelivery(@PathVariable long delivery_id) throws JSONException {
		deliveryController.completeDelivery(delivery_id);
	}
	
	@DeleteMapping(value = "/deliveries/{delivery_id}")
	public void deleteDelivery(@PathVariable long delivery_id) throws JSONException {
		deliveryController.deleteDelivery(delivery_id);
	}
	
}
