package com.products.service.elevatemartproductsservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Inventory {
    //todo we will not use at this moments
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private String state;
    private String country;
    private String countryCode;
    private Long postalCode;
    private String latitude;
    private String longitude;
    private String address1;
    private String address2;
    private String managerName;
    private String managerContactNumber;

}
