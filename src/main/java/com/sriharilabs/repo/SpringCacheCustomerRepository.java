package com.sriharilabs.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sriharilabs.model.Customer;

public interface SpringCacheCustomerRepository extends MongoRepository<Customer, String> {
	@Cacheable("customer")
    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
