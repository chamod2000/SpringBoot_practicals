package com.springboot.productManagment.product.repository;

import com.springboot.productManagment.product.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

//    @Query("SELECT p FROM Product p WHERE p.productName=?1")
//    Optional<Product> findByProductName(String pName);

}
