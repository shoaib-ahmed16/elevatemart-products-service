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
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private  Long attributeId;
    private String type;
    private String value;
    private String name;
    private Boolean active;

    @ManyToMany(mappedBy = "attributeList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> groupList;

}
