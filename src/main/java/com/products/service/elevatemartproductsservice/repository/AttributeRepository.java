package com.products.service.elevatemartproductsservice.repository;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Long> {
}
