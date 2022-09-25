package com.dropit.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class ExternalAPIController {
	
	private final String GEO_API_KEY = "fa8de16ca3ce42cfacaacaf142e88845";
	
	private final String GEO_API_URL = "https://api.geoapify.com/v1/geocode/search?text={text}&apiKey=" + GEO_API_KEY;
	
	private final String HOLIDAY_API_KEY = "bb7e7abc-a679-4080-9cd6-ba79a6e26d46";
	
	private final String HOLIDAY_API_URL = "https://holidayapi.com/v1/holidays?pretty&key=" + HOLIDAY_API_KEY + "&country=IL";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	Logger logger = LoggerFactory.getLogger(ExternalAPIController.class);
	
	/**
	 * 
	 * @param address
	 * @return
	 */
	public String getGeoData(String address) {
		String response = "";
		try {
			String url = GEO_API_URL.replace("{text}", address);
			response = restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			logger.error("Error at getGeoData", e);
		}
		return response;
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONException 
	 */
	public JSONArray getHolidaysData() throws JSONException {
		LocalDate dt = LocalDate.now();   
		LocalDate nextSaturday = dt.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		
		int year = dt.getYear();
		int month = dt.getMonthValue();
		
		// As mentioned in the task, "upcoming week" can be in the next month/year
		// so we need to cover this case
		
		int nextSaturdayYear = nextSaturday.getYear();
		int nextSaturdayMonth = nextSaturday.getMonthValue();
		
		String url;
		JSONArray holidaysJsonArray = new JSONArray();

		
		if(nextSaturdayYear != year || nextSaturdayMonth != month) {
			url = HOLIDAY_API_URL + "&year=" + nextSaturdayYear + "&month=" + nextSaturdayMonth;
			addHolidaysFromAPI(holidaysJsonArray, url);
		}
		
		
		url = HOLIDAY_API_URL + "&year=" + year + "&month=" + month;
		addHolidaysFromAPI(holidaysJsonArray, url);
		
		return holidaysJsonArray;
	}
	
	/**
	 * 
	 * @param holidaysJsonArray
	 * @param url
	 * @throws JSONException
	 */
	private void addHolidaysFromAPI(JSONArray holidaysJsonArray, String url) throws JSONException {
		try {
			String response = restTemplate.getForObject(url, String.class);
			JSONObject responseJsonObject = new JSONObject(response);
			JSONArray holidays = new JSONArray(getPropertyFromJsonObject(responseJsonObject, "holidays"));
			addObjectsToJsonArray(holidaysJsonArray, holidays);
		} catch (Exception e) {
			logger.error("Error at addHolidaysFromAPI.", e);
		}
	}
	
	/**
	 * 
	 * @param array
	 * @param extra
	 * @return
	 * @throws JSONException
	 */
	private JSONArray addObjectsToJsonArray(JSONArray array, JSONArray extra) throws JSONException {
		for(int i = 0; i < extra.length(); i++) {
			array.put(extra.get(i));
		}
		return array;
	}
	
	/**
	 * 
	 * @param object
	 * @param property
	 * @return
	 */
	public String getPropertyFromJsonObject(JSONObject object, String property) {
		String result = "";
		try {
			result = object.getString(property);
		} catch (Exception e) {}
		return result;
	}
}
