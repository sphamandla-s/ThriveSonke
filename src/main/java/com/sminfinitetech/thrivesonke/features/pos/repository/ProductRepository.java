package com.sminfinitetech.thrivesonke.features.pos.repository;

import com.sminfinitetech.thrivesonke.features.pos.model.Category;
import com.sminfinitetech.thrivesonke.features.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);
    List<Product> findByBrand(String brand);
}
