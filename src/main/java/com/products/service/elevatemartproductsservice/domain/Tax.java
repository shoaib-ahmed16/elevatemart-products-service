package com.products.service.elevatemartproductsservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id")
    private Long taxId;
    private String type;
    private  String code;
    private String displayName;
    private Double percent;

    @ManyToMany(mappedBy = "taxList", cascade = CascadeType.ALL)
    private List<Product> productList;

}
