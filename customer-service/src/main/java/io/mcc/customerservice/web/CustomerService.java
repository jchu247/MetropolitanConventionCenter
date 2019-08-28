package io.mcc.customerservice.web;

import io.mcc.customerservice.model.CustomerList;
import io.mcc.customerservice.model.UserData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class CustomerService {

    private List<CustomerList>  customers = new ArrayList<>(Arrays.asList(
            new CustomerList("Jimmy", "mcs@gmail.com"),
            new CustomerList("3hhh", "fasfsa@gmail.com"),
            new CustomerList("Joe", "chu_joe@bah.com"),
            new CustomerList("Mohamed", "Said_mohamed@bah.com")
    ));




    // Get all account
    public List<CustomerList> getAllCustomer() {

        UserData userData = new UserData();
        userData.setUserData(customers);
        return customers;
    }

    // Creat account
    public void addCustomer(CustomerList customerList) {
        customers.add(customerList);

    }

    // Get customer by name
    public CustomerList getCustomer(String name) {

      return customers.stream().filter(t-> t.getName().equals(name)).findFirst().get();
    }

    // update Customer
    public void updateCustomer(CustomerList customerName, String userName) {
        for(int i = 0; i < customers.size(); i++) {
            CustomerList customer = customers.get(i);
            if(customer.getName().equals(userName)){
                customers.set(i, customerName);
                return;
            }
        }
    }

    // Delete Customer
    public void deleteTopic(String name) {
        customers.removeIf(t-> t.getName().equals(name));
    }




}
