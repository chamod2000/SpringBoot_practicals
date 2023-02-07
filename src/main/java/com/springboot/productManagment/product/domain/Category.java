package com.springboot.productManagment.product.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Products> products;

}
