package com.products.service.elevatemartproductsservice.domain;

import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(unique = true)
    private String sourceCode;

    @Column(unique = true)
    private String invetoryName;
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
