package com.springboot.productManagment.product.repository;

import com.springboot.productManagment.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c WHERE c.categoryName=?1")
    Optional<Category> findBycategoryName(String c_name);
}
