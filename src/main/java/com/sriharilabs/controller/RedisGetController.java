package com.sriharilabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sriharilabs.RedisRepositoryImpl;
import com.sriharilabs.model.Customer;

import lombok.extern.slf4j.Slf4j;
//import net.rossillo.spring.web.mvc.CacheControl;

@RestController
@Slf4j
public class RedisGetController {

	@Autowired
	RedisRepositoryImpl redisRepositoryImpl;
	
	

		
	@GetMapping("/getRedisRecords")
	public void getRedisRecords( @RequestParam String name) { 
	    
		try {
			log.info("checking record.....");
			 redisRepositoryImpl.get();
			//return customerRepository.findByFirstName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
}
