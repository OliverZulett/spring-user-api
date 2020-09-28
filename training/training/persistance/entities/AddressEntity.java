package com.milankas.training.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private UUID id;

    @Column(name="address_line_1")
    private String addressLine1;

    @Column(name="address_line_2")
    private String addressLine2;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="country_code")
    private String countryCode;

}
