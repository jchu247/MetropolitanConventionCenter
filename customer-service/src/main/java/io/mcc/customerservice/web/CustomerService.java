package io.mcc.customerservice.web;

import io.mcc.customerservice.model.CustomerList;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CustomerService {

    private List<CustomerList>  customers = new ArrayList<>(Arrays.asList(
            new CustomerList("Jimmy", "mcs@gmail.com"),
            new CustomerList("hhh", "fasfsa@gmail.com")
    ));


    // Get all account
    public List<CustomerList> getAllCustomer() {
        return customers;
    }

    // Creat account
    @PostMapping("/create")
    @ResponseBody
    public void addCustomer(CustomerList customerList) {
        customers.add(customerList);

    }



}
