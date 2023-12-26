package com.hammerdev.customersapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hammerdev.customersapi.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Optional<Customer> findByUsername(String username);
}
