package com.joa.retailstorev1;


import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.dto.CustomerDTO;
import com.joa.retailstorev1.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerResource {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.get(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody Customer customer) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.create(customer));
    }
}
