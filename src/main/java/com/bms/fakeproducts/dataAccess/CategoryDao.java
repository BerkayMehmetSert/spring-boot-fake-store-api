package com.bms.fakeproducts.dataAccess;

import com.bms.fakeproducts.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    boolean existsByCategoryName(String categoryName);
}
