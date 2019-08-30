package io.mcc.customerservice.web;

import io.mcc.customerservice.Services.CustomerService;
import io.mcc.customerservice.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody  Customer customer, BindingResult result) {
        Customer cs = customerService.addOrUpdate(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }



        @GetMapping("/{name}")
        public ResponseEntity<?> getCustomerById(@PathVariable String name) {
            Customer cs = customerService.getCustomerById(name);

            return new ResponseEntity<Customer>(cs, HttpStatus.OK);
        }

        @GetMapping("/all")
        public Iterable<?> getCustomers(){
            return customerService.getAllCustomer();
        }

        @DeleteMapping("/{name}")
        public ResponseEntity<?> deleteCustomer(@PathVariable String name) {
            customerService.deleteCustomerByName(name);
            return new ResponseEntity<String>("Customer Name:'"+name+"' Deleted", HttpStatus.OK);
        }

}
