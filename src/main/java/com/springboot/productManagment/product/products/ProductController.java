package com.springboot.productManagment.product.products;


import com.springboot.productManagment.product.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/getallproducts")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping(path = "/insertproduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProducts(product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delectProduct(@PathVariable("id") Long pid){

        productService.deleteProduct(pid);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProdcut(@PathVariable Long productId, @RequestBody Product productDetails){
       return productService.update(productId,productDetails);
    }



}
