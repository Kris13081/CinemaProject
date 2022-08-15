package com.kris.cinema.service;

import com.kris.cinema.model.Customer;
import com.kris.cinema.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.findById(customerId);
        boolean exists = customerRepository.existsById(customerId);
        if(exists){
            throw  new IllegalStateException(
                    "customer with id " + customerId + " does not exists!"
            );
        }
        customerRepository.deleteById(customerId);
    }
    @Transactional
    public void  updateCustomer(Long customerId,
                                String name,
                                String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "customer with id " + customerId + " doesn't exists!"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository
                    .findCustomerByEmail(email);
            if (customerOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            customer.setEmail(email);
        }
    }
}
