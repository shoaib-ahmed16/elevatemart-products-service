package com.products.service.elevatemartproductsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(unique = true)
    private  String code;
    @Column(unique = true)
    private String displayName;
    private Double percent;

    @ManyToMany(mappedBy = "taxes", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    @Override
    public String toString() {
        return "Tax{" +
                "taxId=" + taxId +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", displayName='" + displayName + '\'' +
                ", percent=" + percent +
                '}';
    }
}
