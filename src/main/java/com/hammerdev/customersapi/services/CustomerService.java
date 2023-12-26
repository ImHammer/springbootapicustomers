package com.hammerdev.customersapi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hammerdev.customersapi.dtos.CustomerInputDTO;
import com.hammerdev.customersapi.models.Customer;
import com.hammerdev.customersapi.repositories.CustomerRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Size;

@Service
public class CustomerService
{
    @Autowired
    Validator validator;

    @Autowired
    protected CustomerRepository customerRepository;

    public Customer createCustomer(CustomerInputDTO customerInputDTO)
    {
        Set<ConstraintViolation<CustomerInputDTO>> errors = validator.validate(customerInputDTO);

        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.iterator().next().getMessage());
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerInputDTO, customer, "birthDate");

        LocalDate customerBirthDate = LocalDate.parse(customerInputDTO.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        customer.setBirthDate(customerBirthDate);
        
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(customer.getUpdatedAt()); // SE COLOCAR O LocalDateTime.now() TEM DIFERENÇA EM MILISEGUNDDOS

        this.customerRepository.save(customer);

        if (customer.getId() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return customer;
    }

    public Customer updateCustomer(Long customerId, CustomerInputDTO customerInputDTO)
    {   
        Set<ConstraintViolation<CustomerInputDTO>> errors = validator.validate(customerInputDTO);

        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.iterator().next().getMessage());
        }

        Customer customer = this.customerRepository.findById(customerId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NO_CONTENT)
        );

        BeanUtils.copyProperties(customerInputDTO, customer, "id", "password", "createdAt", "birthDate");
        customer.setUpdatedAt(LocalDateTime.now());

        this.customerRepository.saveAndFlush(customer);

        return customer;
    }

    public void deleteCustomer(Long customerId)
    {
        this.customerRepository.deleteById(customerId);
    }

    public Collection<Customer> getCustomers()
    {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId)
    {
        return this.customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
