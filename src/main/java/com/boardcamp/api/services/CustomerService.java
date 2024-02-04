package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerModel findById(Long id){
        return customerRepository.findById(id).orElseThrow(
           () -> new CustomerNotFoundException("Usuário não encontrado!")
        );
    }
}
