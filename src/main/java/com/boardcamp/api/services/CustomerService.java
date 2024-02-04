package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.CustomerConflictException;
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

    public CustomerModel save(CustomerDTO dto) {
        if (customerRepository.existsByCpf(dto.getCpf())) {
            throw new CustomerConflictException("Esse cpf já está cadastrado");
        }

        CustomerModel customer = new CustomerModel(dto);
        return customerRepository.save(customer);

    }
}
