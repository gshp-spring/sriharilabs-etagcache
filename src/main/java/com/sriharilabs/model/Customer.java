package com.sriharilabs.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Document
@Setter
@Getter
@ToString
public class Customer implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    public String firstName;
    public Customer(String firstName, String lastName, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	public String lastName;
    public String address;
    
    public Customer() {
    	
    }
  

}

