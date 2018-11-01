package com.sriharilabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sriharilabs.model.Customer;
import com.sriharilabs.repo.SpringCacheCustomerRepository;

import lombok.extern.slf4j.Slf4j;
//import net.rossillo.spring.web.mvc.CacheControl;

@Controller
@Slf4j
public class SpringCachingController {

	@Autowired
	SpringCacheCustomerRepository springCacheCustomerRepository;
	
	Customer cust;
	
	
	@GetMapping("/getCacheRecords")
	@Cacheable("customer")
	public @ResponseBody Customer getUser(@RequestParam String name) { 
		
		try {
			log.info("its getCacheRecords.....");
			cust =springCacheCustomerRepository.findByFirstName(name);
			System.out.println("koti........"+cust.getFirstName());
			//return customerRepository.findByFirstName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cust;
	}
	
	@GetMapping("/getCacheform")
	public String getform() { 
	
		
		return "springcache";
	}
	
	
}
