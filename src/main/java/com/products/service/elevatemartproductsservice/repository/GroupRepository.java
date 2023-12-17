package com.products.service.elevatemartproductsservice.repository;

import com.products.service.elevatemartproductsservice.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
