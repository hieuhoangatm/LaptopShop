package com.dinhhieu.JavaSpringUltimate.repository;

import com.dinhhieu.JavaSpringUltimate.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
