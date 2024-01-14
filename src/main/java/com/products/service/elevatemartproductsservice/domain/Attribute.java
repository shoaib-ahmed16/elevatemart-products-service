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
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private  int attributeId;
    private String type;
    private String value;
    @Column(unique = true)
    private String name;
    private boolean active;

    @ManyToMany(mappedBy = "attributes", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Category> categories;

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + attributeId +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
