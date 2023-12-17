package com.products.service.elevatemartproductsservice.repository;

import com.products.service.elevatemartproductsservice.domain.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax,Long> {
}
