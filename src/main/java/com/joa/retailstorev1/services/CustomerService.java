package com.joa.retailstorev1.services;

import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.dto.CustomerDTO;
import com.joa.retailstorev1.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO create(Customer customer) {
        customerRepository.save(customer);
        return CustomerDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }

    public Customer get(Long id) {
        return customerRepository.findById(id)
                .get();
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
