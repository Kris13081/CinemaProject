package com.kris.cinema.service;

import com.kris.cinema.model.Customer;
import com.kris.cinema.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public  CustomerService(CustomerRepository customerRepository){this.customerRepository = customerRepository;}


    public List<Customer> getCustomer() {
        return  customerRepository.findAll();
    }
    public void  addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByName(customer.getName());
        customerRepository.save(customer);
    }
}
