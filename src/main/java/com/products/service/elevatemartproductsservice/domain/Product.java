package com.products.service.elevatemartproductsservice.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private  Long productId;

    @Column(unique = true)
    private String sku;

    private String name;
    private String type;
    private Boolean isTaxable;
    private Double price;
    private String imageUrl;
    @ManyToMany
    @JoinTable(name = "product_tax",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tax_id"))
    private List<Tax> taxes;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id")
    )
    private List<Category> categories;
    private Long quantities;
    private Long thresholdQuantities;
    private Boolean isActive;

}
