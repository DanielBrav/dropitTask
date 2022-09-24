package com.dropit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropit.entities.AddressEntity;
import com.dropit.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public AddressEntity findById(long id) {
		return addressRepository.findById(id).get();
	}
}
