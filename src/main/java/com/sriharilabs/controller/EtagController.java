package com.sriharilabs.controller;

import java.time.Instant;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.sriharilabs.model.Customer;
import com.sriharilabs.repo.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EtagController {

	@Autowired
	CustomerRepository customerRepository;
	@Bean
	Filter shallowEtagFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
    @GetMapping("/hi")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        log.info(" "+Instant.now());
        try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "etag-cache";
    }

    @GetMapping("/etagform")
	//@CacheControl(maxAge=31556926)
	public String getform() { 
	
		
		return "etag-form";
	}
    
    @GetMapping("/getEtagRecords")
	//@CacheControl(maxAge=31556926)
    public @ResponseBody Customer get(@RequestParam String name,Model model ) {
		//System.out.println("gshp "+ht.ETAG+"    valuve"+ht.ETAG.);
		
		log.info("getEtagRecords ........Getting Records");
		log.info(""+Instant.now());
		try {
			Thread.sleep(100);
			return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
    
   
}
