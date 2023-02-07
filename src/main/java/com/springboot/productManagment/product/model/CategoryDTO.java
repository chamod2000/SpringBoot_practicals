package com.springboot.productManagment.product.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDTO {

    private Long id;
    @NotNull
    @Size(max = 255)
    private String categoryName;


}
