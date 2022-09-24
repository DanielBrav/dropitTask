package com.dropit.rest;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dropit.controller.AddressController;
import com.dropit.controller.DeliveryController;
import com.dropit.controller.TimeslotController;
import com.dropit.data.AddressData;
import com.dropit.data.DeliveryData;
import com.dropit.data.TimeslotData;

@Controller
@ResponseBody
public class DeliveryRestController {
	
	@Autowired
	private AddressController addressController;
	
	@Autowired
	private TimeslotController timeslotController;

	@Autowired
	private DeliveryController deliveryController;
	
	@RequestMapping(value = "/resolve-address", method = RequestMethod.POST)
	@ResponseBody
	public AddressData resolveAddress(@RequestBody String address) throws JSONException {
	    return addressController.resolveAddress(address);
	}
	
	@RequestMapping(value = "/timeslots", method = RequestMethod.POST)
	@ResponseBody
	public List<TimeslotData> timeslots(@RequestBody AddressData address) throws JSONException {
	    return timeslotController.retrieveAvailableTimeslots(address);
	}
	
	@RequestMapping(value = "/deliveries", method = RequestMethod.POST)
	@ResponseBody
	public void deliveries(@RequestBody DeliveryData delivery) throws JSONException {
		deliveryController.addDelivery(delivery);
	}
	
	@RequestMapping(value = "/deliveries/{delivery_id}/complete", method = RequestMethod.POST)
	@ResponseBody
	public void completeDelivery(@PathVariable long delivery_id) throws JSONException {
		deliveryController.completeDelivery(delivery_id);
	}
	
	@RequestMapping(value = "/deliveries/{delivery_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteDelivery(@PathVariable long delivery_id) throws JSONException {
		deliveryController.deleteDelivery(delivery_id);
	}
}
