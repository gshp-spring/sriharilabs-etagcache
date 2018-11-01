package com.sriharilabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sriharilabs.model.Customer;
import com.sriharilabs.repo.SpringCacheCustomerRepository;

import lombok.extern.slf4j.Slf4j;
//import net.rossillo.spring.web.mvc.CacheControl;

@RestController
@Slf4j
public class SaveController {

	@Autowired
	SpringCacheCustomerRepository springCacheCustomerRepository;
	
	

		
	@PostMapping("/saveCacheRecords")
	@Cacheable("customer")
	public void saveCacheRecords(@RequestBody Customer customer) { 
	    
		try {
			log.info("saving record.....");
			springCacheCustomerRepository.save(customer);
			//return customerRepository.findByFirstName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
}
