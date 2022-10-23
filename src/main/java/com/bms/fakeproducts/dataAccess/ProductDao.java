package com.bms.fakeproducts.dataAccess;

import com.bms.fakeproducts.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
    boolean existsByProductName(String productName);
}
