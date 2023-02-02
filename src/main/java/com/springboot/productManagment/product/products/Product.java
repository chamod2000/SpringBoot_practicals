package com.springboot.productManagment.product.products;

import com.springboot.productManagment.product.category.Category;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long productId;
    private String productName;
    private double productPrice;

}
