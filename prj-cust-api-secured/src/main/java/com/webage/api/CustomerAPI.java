package com.webage.api;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

import com.webage.exceptions.CustomerIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.webage.domain.Customer;
import com.webage.logging.ApiLogger;
import com.webage.repository.CustomersRepository;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {
   @Autowired
   CustomersRepository repo;

   public Customer getCustomerByName(String name) {
      Customer cs = repo.findCustomerByName(name);
      if(cs == null) {
         throw new CustomerIdException("Customer Name: '" +name+"' DOES EXIST");
      }
      return cs;
   }

   public Customer getCustomerByEmail(String email) {
      Customer cs = repo.findCustomerByEmail(email);
      if(cs == null) {
         throw new CustomerIdException("Customer Email: '" +email+"' DOES EXIST");
      }
      return cs;
   }

   @GetMapping
   public Iterable<Customer> getAll() {
      return repo.findAll();
   }

   @GetMapping("/{customerId}")
   public Optional<Customer> getCustomerById(@PathVariable("customerId") long id) {
      //return repo.findOne(id);
      Customer cus = repo.findAllById(id);
      if(cus == null){
         throw new CustomerIdException("Customer ID: '"+id+"' Doest not exist try again");
      }


      return repo.findById(id);
   }
   
   @PostMapping
   public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {

      if (newCustomer.getId() != 0 || newCustomer.getName() == null|| newCustomer.getEmail() == null) {
         // Reject we'll assign the customer id
         //return ResponseEntity.badRequest().build();
         throw new CustomerIdException("Invalid input try again");


      }
      newCustomer = repo.save(newCustomer);
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newCustomer.getId()).toUri();
      ResponseEntity<?> response = ResponseEntity.created(location).build();
      return response;
   }

   //lookupCustomerByName GET
   @GetMapping("/byname/{username}")
   public ResponseEntity<?> lookupCustomerByNameGet(@PathVariable("username") String username,
         UriComponentsBuilder uri) {
      ApiLogger.log("username: " + username);
      
      Iterator<Customer> customers = repo.findAll().iterator();
      while(customers.hasNext()) {
         Customer cust = customers.next();
         if(cust.getName().equalsIgnoreCase(username)) {
            ResponseEntity<?> response = ResponseEntity.ok(cust);
            return response;            
         }        
      }

      //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      return new ResponseEntity<String>("Customer Name:'"+username+"' Does not exist in the database", HttpStatus.OK);


   }
   
   //lookupCustomerByName POST
   //Double check
   @PostMapping("/byname")
   public ResponseEntity<?> lookupCustomerByNamePost(@RequestBody String username, UriComponentsBuilder uri) {

      ApiLogger.log("username: " + username);
      Iterator<Customer> customers = repo.findAll().iterator();
      while(customers.hasNext()) {
         Customer cust = customers.next();
         if(cust.getName().equals(username)) {
            ResponseEntity<?> response = ResponseEntity.ok(cust);
            return response;            
         }        
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      //return new ResponseEntity<String>("Customer Name:'"+username+"' Does not exist in the database", HttpStatus.OK);
   }  
   
   
   @PutMapping("/{customerId}")
   public ResponseEntity<?> putCustomer(
         @RequestBody Customer newCustomer,
         @PathVariable("customerId") long customerId) 
   {
      if (newCustomer.getId() != customerId || newCustomer.getName() == null || newCustomer.getEmail() == null) {
         //return ResponseEntity.badRequest().build();
         throw new CustomerIdException("Customer ID: '"+customerId+"' Doest not exist in the Database");
      }
      newCustomer = repo.save(newCustomer);
      return ResponseEntity.ok().build();
   }  
   
   @DeleteMapping("/{customerId}")
   public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long id) {
      Customer cs = repo.findAllById(id);
      if(cs == null){
         throw new CustomerIdException("Customer ID: '"+id+"' Doest not exist in the Database");
      }
      // repo.delete(id);
      repo.deleteById(id);
      //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      return new ResponseEntity<String>("Customer ID:'"+id+"' Deleted", HttpStatus.OK);
   }


   
   
}