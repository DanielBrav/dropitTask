package com.dropit.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;

import com.dropit.data.AddressData;
import com.dropit.data.HolidayData;
import com.dropit.data.TimeslotData;
import com.dropit.entities.TimeslotAddressEntity;
import com.dropit.entities.TimeslotEntity;
import com.dropit.service.TimeslotAddressService;
import com.dropit.service.TimeslotService;

@Controller
public class TimeslotController {

	@Autowired
	private TimeslotService timeslotService;
	
	@Autowired
	private ExternalAPIController externalAPIController;

	@Autowired
	private TimeslotAddressService timeslotAddressService;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	private SimpleDateFormat timeslotFileFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private final String TIMESLOTS_JSON = "timeslots.json";
	
	@PostConstruct
	private void init() {
		try {
			File file = ResourceUtils.getFile("classpath:"+TIMESLOTS_JSON);
			//Read File Content
			String content = new String(Files.readAllBytes(file.toPath()));
			List<TimeslotData> timeslotsFromFile = parseTimeslotJsonToObjects(content);
			
			timeslotsFromFile = filterHolidayTimeslots(timeslotsFromFile);
			
			saveTimeslots(timeslotsFromFile);
			
		} catch(Exception e) {
			System.out.println("Error on load and parse timeslots");
		}
	}

	/**
	 * 
	 * @param timeslotId
	 * @return
	 */
	public synchronized boolean bookTimeslot(long timeslotId) {
		TimeslotEntity timelsot = timeslotService.findById(timeslotId);
		if(timelsot.getBookedDeliveriesAmount() < 2) {
			timelsot.setBookedDeliveriesAmount(timelsot.getBookedDeliveriesAmount()+1);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param timeslots
	 */
	private void saveTimeslots(List<TimeslotData> timeslots) {
		List<String> supportedAddresses;
		
		for(TimeslotData timeslot : timeslots) {
			supportedAddresses = timeslot.getSupportedDeliveryStates();
			
			TimeslotEntity timeslotEntity = new TimeslotEntity(timeslot);
			timeslotEntity = timeslotService.save(timeslotEntity);
			
			long timeslotEntityId = timeslotEntity.getId();
			
			supportedAddresses.forEach(state -> {
				TimeslotAddressEntity tace = new TimeslotAddressEntity();
				tace.setTimeslotId(timeslotEntityId);
				tace.setSupportedDeliveryState(state);
				timeslotAddressService.save(tace);
			});

		}
	}
	
	/**
	 * 
	 * @param jsonFileContent
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private List<TimeslotData> parseTimeslotJsonToObjects(String jsonFileContent) throws JSONException, ParseException {
		List<TimeslotData> timeslotDataList = new ArrayList<>();
		JSONArray supportedAddressesJson, timeslotsJsonArray = new JSONArray(jsonFileContent);
		JSONObject timeslotJson;
		for(int i = 0; i < timeslotsJsonArray.length(); i++) {

			timeslotJson = timeslotsJsonArray.getJSONObject(i);
			long startTime = getTimeFromString(externalAPIController.getPropertyFromJsonObject(timeslotJson, "startTime"), timeslotFileFormatter);
			long endTime = getTimeFromString(externalAPIController.getPropertyFromJsonObject(timeslotJson, "endTime"), timeslotFileFormatter);
	
			TimeslotData timeslotData = new TimeslotData(startTime, endTime);
			supportedAddressesJson = timeslotJson.getJSONArray("supportedDeliveryStates");
			
			for(int j = 0; j < supportedAddressesJson.length(); j++) {
				String state = supportedAddressesJson.getString(j);
				timeslotData.getSupportedDeliveryStates().add(state);
			}
			
			timeslotDataList.add(timeslotData);
		}
		
		return timeslotDataList;
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	private long getTimeFromString(String dateString, SimpleDateFormat formatter) throws ParseException {
		Date date = formatter.parse(dateString);
		return date.getTime();
	}
	
	/**
	 * 
	 * @param addressId
	 * @return
	 */
	public List<TimeslotData> retrieveAvailableTimeslots(AddressData address) {
		
		List<Long> timeslotIds = timeslotAddressService.getTimeslotsByState(address.getState());
		List<TimeslotEntity> timeslotEntities = timeslotIds.stream()
															.map(id -> timeslotService.findById(id))
															.collect(Collectors.toList());
		
		List<TimeslotData> result = timeslotEntities.stream()
													.map(td -> getTimeslotDataFromEntity(td))
													.collect(Collectors.toList());
		
		return result;
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	private TimeslotData getTimeslotDataFromEntity(TimeslotEntity entity) {
		TimeslotData td = new TimeslotData(entity.getStartTime(), entity.getEndTime());
		List<TimeslotAddressEntity> timeslotAddressEntities = timeslotAddressService.findByTimeslotId(entity.getId());
		
		timeslotAddressEntities.forEach(tae -> td.getSupportedDeliveryStates().add(tae.getSupportedDeliveryState()));
		return td;
	}
	
	/**
	 * 
	 * @param holidays
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	private List<HolidayData> convertHolidaysAPIResponseJsonToHolidaysData(JSONArray holidays) throws ParseException, JSONException {
		
		List<HolidayData> holidaysList = new ArrayList<>();
		for(int i = 0; i < holidays.length(); i++) {
			JSONObject holiday = holidays.getJSONObject(i);
			String dateString = externalAPIController.getPropertyFromJsonObject(holiday, "date");
			holidaysList.add(new HolidayData(getTimeFromString(dateString, formatter)));
		}
		
		// sort holidays list
		holidaysList = holidaysList.stream()
									.sorted(Comparator.comparing(HolidayData::getStartTime))
									.collect(Collectors.toList());

		return holidaysList;
	}
	
	/**
	 * 
	 * @param timeslotDataObjects
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	public List<TimeslotData> filterHolidayTimeslots(List<TimeslotData> timeslotDataObjects) throws JSONException, ParseException {

		JSONArray holidaysJson = externalAPIController.getHolidaysData();
		
		List<HolidayData> holidaysList = convertHolidaysAPIResponseJsonToHolidaysData(holidaysJson);
		
		List<TimeslotData> timeslotDataList = new ArrayList<>();
		
		timeslotDataList = timeslotDataObjects.stream()
				.sorted(Comparator.comparing(TimeslotData::getStartTime))
				.filter(tsd -> !timeslotOverlapsHoliday(tsd, holidaysList))
				.collect(Collectors.toList());
		
		return timeslotDataList;
	}
	
	/**
	 * 
	 * @param tsd
	 * @param holidaysList
	 * @return
	 */
	private boolean timeslotOverlapsHoliday(TimeslotData tsd, List<HolidayData> holidaysList) {
		return holidaysList.stream().anyMatch(holiday -> {
			if(tsd.getStartTime() <= holiday.getEndTime() 
				&& holiday.getStartTime() <= tsd.getEndTime()) {
				return true;
			}
			return false;
		});
	}
	
}
