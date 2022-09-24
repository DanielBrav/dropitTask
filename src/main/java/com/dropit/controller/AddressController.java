package com.dropit.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dropit.data.AddressData;
import com.dropit.entities.AddressEntity;
import com.dropit.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private ExternalAPIController externalAPIController;

	/**
	 * 
	 * @param address
	 * @return
	 * @throws JSONException
	 */
	public AddressData resolveAddress(String address) throws JSONException {
		String response = externalAPIController.getGeoData(address);
		JSONObject responeJson = new JSONObject(response);

		AddressData result = null;
		
		try {
			JSONObject properties = responeJson.getJSONArray("features").getJSONObject(0).getJSONObject("properties");
			String state = externalAPIController.getPropertyFromJsonObject(properties, "state");
			String street = externalAPIController.getPropertyFromJsonObject(properties, "street");
			String line1 = externalAPIController.getPropertyFromJsonObject(properties, "address_line1");
			String line2 = externalAPIController.getPropertyFromJsonObject(properties, "address_line2");
			String country = externalAPIController.getPropertyFromJsonObject(properties, "country");
			String postcode = externalAPIController.getPropertyFromJsonObject(properties, "postcode");
			result = new AddressData(street, line1, line2, country, postcode, state);
		} catch (Exception e) {}
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AddressEntity findById(long id) {
		return addressService.findById(id);
	}
}
