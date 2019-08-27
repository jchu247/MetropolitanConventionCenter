package io.mcc.customerservice.web;

import io.mcc.customerservice.model.CustomerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerList customerList;

    @Autowired CustomerService customerService;

    @RequestMapping("/userName")
    public List<CustomerList> getCustomer(@PathVariable String userName) {
        return (List<CustomerList>) new CustomerList("Test", "mohamed.a.said@gmail.com");

    }


    @GetMapping("/all")
    @ResponseBody
    public List<CustomerList> getAllCustiomer() {
        return customerService.getAllCustomer();
    }






}
