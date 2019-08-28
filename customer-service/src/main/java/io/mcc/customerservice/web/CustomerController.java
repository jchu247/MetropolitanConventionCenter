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


    //update customer
    @PutMapping("/{userName}")
    @ResponseBody
    public void updateCustomer(@RequestBody CustomerList customerList, @PathVariable String userName) {
        customerService.updateCustomer(customerList, userName);
    }

    //Delete customer
    @DeleteMapping("/{userName}")
    @ResponseBody
    public void deleteCustomer( @PathVariable String userName) {
        customerService.deleteTopic(userName);
    }


    /*
    @GetMapping("/{userId}")
    @ResponseBody
    public UserData getUserByName(String userId) {
        List<CustomerList> users = new ArrayList<>(Arrays.asList(
                new CustomerList("Joe", "chu_joe@bah.com"),
                new CustomerList("Mohamed", "Said_mohamed@bah.com")
        ));

        UserData userData = new UserData();
        userData.setUserData(users);
        return userData;
    }

     */












}
