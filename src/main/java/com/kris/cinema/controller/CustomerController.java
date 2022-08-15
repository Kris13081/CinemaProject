package com.kris.cinema.controller;

import com.kris.cinema.model.Customer;
import com.kris.cinema.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public  CustomerController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping
    public  void registerNewCustomer(@RequestBody Customer customer) {customerService.addNewCustomer(customer);}

    @DeleteMapping(path = "{customerId}")
    public void  deleteCustomer(
            @PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
    }
    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @PathVariable(required = false) String name,
            @PathVariable(required = false) String email) {
        customerService.updateCustomer(customerId, name, email);
    }
}
