package com.springboot.productManagment.product.domain;

import com.springboot.productManagment.product.domain.Category;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Products {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long productId;

    @Column(nullable = false, unique = true)
    private String productName;

    @Column(nullable = false)
    private Double productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
