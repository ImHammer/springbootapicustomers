package com.hammerdev.customersapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hammerdev.customersapi.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>
{
    
}
