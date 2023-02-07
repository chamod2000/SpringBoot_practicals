package com.springboot.productManagment.product.controller;


import com.springboot.productManagment.product.service.ProductService;
import com.springboot.productManagment.product.model.ProductsDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;


    @Operation(summary = "his is to fetch all the products stored in database")
    @GetMapping(path = "/read/all")
    public List<ProductsDTO> getAllProduct(){
        return productService.getAllProduct();
    }


    @Operation(summary = "This is to fetch product data by selected id in database")
    @GetMapping(path = "/read/{productId}")
    public ProductsDTO getProductById(@PathVariable ("productId") Long pid){
        ProductsDTO productsDTO = productService.getProductByID(pid);
        return productsDTO;
    }

    @Operation(summary = "This is to fetch all data by pagination")
    @GetMapping(path = "/read/pagination/{offset}/{pageSize}")
    public List<ProductsDTO> getProductWithPagination (@PathVariable Integer offset, @PathVariable Integer pageSize){
        return  productService.findproductWithPagination(offset,pageSize);
    }

    @Operation(summary = "This is to sort and paginate all data")
    @GetMapping(path = "/read/paginationsort/{offset}/{pageSize}/{field}")
    public List<ProductsDTO> getProductPaginationAndSort(@PathVariable Integer offset, @PathVariable Integer pageSize,@PathVariable String field){
        return productService.findProduct(offset,pageSize,field);
    }


    @Operation(summary = "This is insert new product into the database")
    @PostMapping
    public Long addProduct(@RequestBody ProductsDTO productsDTO){
        return productService.addProducts(productsDTO);
    }

    @Operation(summary = "This is to delete existing product form database")
    @DeleteMapping(path = "/{productId}")
    public void delectProduct(@PathVariable("productId") Long pid){

        productService.deleteProduct(pid);
    }

    @Operation(summary = "This is to update product data")
    @PutMapping("/{productId}")
    public ProductsDTO updateProdcut(@PathVariable Long productId, @RequestBody ProductsDTO productsDTO){
       return productService.update(productId,productsDTO);
    }



}
