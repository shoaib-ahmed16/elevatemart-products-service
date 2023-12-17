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
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "group_attribute",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    private List<Attribute> attributeList;

    @ManyToMany(mappedBy = "groupList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList;
    private Boolean isActive;

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

}
