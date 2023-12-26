package com.hammerdev.customersapi.models;

import com.hammerdev.customersapi.enums.PhoneType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "contact")
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_type", columnDefinition = "VARCHAR(16)")
    private PhoneType phoneType;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(32)")
    private String phoneNumber;

    @Column(name = "customer_id")
    private Long customerId;    
}
