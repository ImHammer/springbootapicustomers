package com.hammerdev.customersapi.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hammerdev.customersapi.dtos.CustomerInputDTO;
import com.hammerdev.customersapi.models.Customer;
import com.hammerdev.customersapi.services.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController
{
    @Autowired
    protected CustomerService customerService;

    @GetMapping
    public Collection<Customer> getCustomers()
    {
        return this.customerService.getCustomers();
    }
    
    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId)
    {
        return this.customerService.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerInputDTO customerInputDTO)
            throws URISyntaxException
    {
        Customer customer = this.customerService.createCustomer(customerInputDTO);
        return ResponseEntity.created(new URI("http://localhost:4000/api/customers/%d".formatted(customer.getId())))
                .body(customer);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerInputDTO customerInputDTO)
    {
        return ResponseEntity.ok().body(
            this.customerService.updateCustomer(customerId, customerInputDTO)
        );
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long customerId)
    {
        this.customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /// LONG IDs QUANDO NÃO CONSEGUEM SER FORMATADOS
    @ExceptionHandler(NumberFormatException.class)
    public void handleNumberFormatException(NumberFormatException exception)
    {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Strings cannot be formatted to numbers");
    }

    @ExceptionHandler(URISyntaxException.class)
    public void handleURISyntaxException(URISyntaxException exception)
    {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
