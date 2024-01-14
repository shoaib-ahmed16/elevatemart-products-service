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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToMany
    @JoinTable(
            name = "category_attribute",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name ="attribute_id")
    )
    private List<Attribute> attributes;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;
    private Boolean isActive;

    @Column(unique = true)
    private String name;

    public void setName(String name) {
        if (name != null) {
            this.name = formatName(name);
        }
    }
    private String formatName(String name) {
        String[] nameArr = name.trim().split(" ");
        StringBuilder sb= new StringBuilder();
        for(String s:nameArr){
            if (s.length() > 1) {
                sb.append(Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase());
            } else if (name.length() == 1) {
                sb.append(s.toUpperCase());
            } else {
                sb.append(s);
            }
        }
        return  new String(sb);
    }
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                '}';
    }
}
