package com.springboot.productManagment.product.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;

    //get all registered products
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    //add new products
    public Product addProducts(Product product) {
        return productRepository.save(product);
    }


    //delete products
    public boolean deleteProduct(Long pid){
        boolean exist = productRepository.existsById(pid);
        if(!exist){
            throw new IllegalStateException("ID Dose not exist");
        }
         productRepository.deleteById(pid);
        return exist;
    }


    //update products
    public ResponseEntity<Product> update(Long productId, Product productDetails) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalStateException("Product not exist with id"));
        product.setProductName(productDetails.getProductName());
        product.setProductPrice(productDetails.getProductPrice());

        productRepository.save(product);
        return ResponseEntity.ok(product);

    }
}
