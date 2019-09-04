package io.mcc.customerservice.repository;

import io.mcc.customerservice.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findCustomerByName(String name);


    @Override
    Iterable<Customer> findAllById(Iterable<Long> iterable);
}
