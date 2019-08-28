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

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/{userName}")
    public CustomerList getCustomer(@PathVariable String userName) {
        //return customerService.
        return  customerList;
    }


    @GetMapping("/all")
    @ResponseBody
    public List<CustomerList> getAllCustiomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/create")
    @ResponseBody
    public void addCustomer(@RequestBody() CustomerList customerList) {
        customerService.addCustomer(customerList);

    }

    @GetMapping("/{name}")
    @ResponseBody
    public CustomerList getByName(@PathVariable("name") String name) {
       return customerService.getCustomer(name);

    }








}
