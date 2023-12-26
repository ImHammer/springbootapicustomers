package com.hammerdev.customersapi.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "customer")
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(16)")
    private String username;

    @Column(columnDefinition = "VARCHAR(32)")
    private String email;

    @Column(name = "first_name", columnDefinition = "VARCHAR(16)")
    private String firstName;
    
    @Column(name = "last_name", columnDefinition = "VARCHAR(16)")
    private String lastName;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "customer_id")
    private Contact contact;
}
