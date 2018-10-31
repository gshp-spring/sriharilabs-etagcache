package com.sriharilabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	

	
	@GetMapping("/getCacheRecords")
	public @ResponseBody ResponseEntity<Customer> getUser(@RequestParam String name) { 
	    
		try {
			System.out.println("its getCacheRecords.....");
			Thread.sleep(1000);
			//return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
	      .body(springCacheCustomerRepository.findByFirstName(name));
	}
	
	@GetMapping("/getCacheform")
	public String getform() { 
	
		
		return "springcache";
	}
}
