package com.springboot.productManagment.product.model;




import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ProductsDTO {

    private Long productId;

    @NotNull
    @Size(max = 255)
    private String productName;
    @NotNull
    private Double productPrice;

    @NotNull
    private Long category_Id;

}
