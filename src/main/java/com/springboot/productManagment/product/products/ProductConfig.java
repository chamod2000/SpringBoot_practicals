package com.springboot.productManagment.product.products;

import com.springboot.productManagment.product.category.Category;
import com.springboot.productManagment.product.category.CategoryConfig;
import com.springboot.productManagment.product.category.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepositoryrepository, CategoryRepository categoryRepository){
        return args -> {

            Category vga = new Category("VGA");
           Category ram = new Category("RAM");


          List<Category> categories = Arrays.asList(vga,ram);
           //categoryRepository.saveAll(categories);

           // Product gtx1050 = new Product("GTX 1050",2000.0, vga);
           // vga.getProducts().add(gtx1050);
          //  productRepositoryrepository.save(gtx1050);
        };
}

}
