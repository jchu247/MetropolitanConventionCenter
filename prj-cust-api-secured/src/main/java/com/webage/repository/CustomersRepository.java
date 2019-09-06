package com.webage.repository;

import org.springframework.data.repository.CrudRepository;

import com.webage.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
	
	Customer findCustomerByEmail(String email);

    Customer findCustomerByName(String name);

    Customer findAllById(Long id);



    @Override
    Iterable<Customer> findAllById(Iterable<Long> iterable);


}
