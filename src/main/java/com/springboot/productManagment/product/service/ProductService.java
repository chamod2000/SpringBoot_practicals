package com.springboot.productManagment.product.service;
import com.springboot.productManagment.product.domain.Category;
import com.springboot.productManagment.product.repository.CategoryRepository;
import com.springboot.productManagment.product.domain.Products;
import com.springboot.productManagment.product.model.ProductsDTO;
import com.springboot.productManagment.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductsDTO> findproductWithPagination(Integer offset, Integer pageSize) {

        final Page<Products> getAllproducts = productRepository.findAll(PageRequest.of(offset,pageSize));
        return getAllproducts.stream()
                .map((product) -> maptoDTO(product, new ProductsDTO()))
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> findProduct(Integer offset, Integer pageSize, String field) {

        final Page<Products> getAllProductWIthSort = productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return getAllProductWIthSort.stream()
                .map((product) -> maptoDTO(product, new ProductsDTO()))
                .collect(Collectors.toList());
    }

    //get all registered products
    public List<ProductsDTO> getAllProduct() {
        final List<Products> getAllproducts =productRepository.findAll();
        return getAllproducts.stream()
                .map((product) -> maptoDTO(product, new ProductsDTO()))
                .collect(Collectors.toList());
    }

    public ProductsDTO getProductByID(Long pid) {

        if (productRepository.existsById(pid)){
            return productRepository.findById(pid).map(products -> maptoDTO(products,new ProductsDTO()))
                    .orElseThrow(()->new IllegalStateException("not found id"));
        }else {
            return null;
        }

    }


    //add new products
    public Long addProducts(ProductsDTO  productsDTO) {
        final Products products = new Products();
        mapToEntity(productsDTO,products);
        return productRepository.save(products).getProductId();

    }


    //delete products
    public void deleteProduct(Long pid){
        boolean exist = productRepository.existsById(pid);
        if(!exist){
            throw new IllegalStateException("ID Dose not exist");
        }
         productRepository.deleteById(pid);
    }


    //update products
    public ProductsDTO update(Long productId, ProductsDTO productsDTO) {

        final Products products =productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product id not found"));
        mapToEntity(productsDTO,products);
        productRepository.save(products);
        return productsDTO;
    }


    private ProductsDTO maptoDTO(final Products products, final ProductsDTO productsDTO){
        productsDTO.setProductId(products.getProductId());
        productsDTO.setProductName(products.getProductName());
        productsDTO.setProductPrice(products.getProductPrice());
        productsDTO.setCategory_Id(products.getCategory() == null ? null : products.getCategory().getId());
        System.out.println("category id : "+productsDTO.getCategory_Id());
        return productsDTO;
    }

    private Products mapToEntity(final ProductsDTO productsDTO, final Products products){
        products.setProductName(productsDTO.getProductName());
        products.setProductPrice(productsDTO.getProductPrice());
        final Category category = productsDTO.getCategory_Id() == null ? null : categoryRepository.findById(productsDTO.getCategory_Id())
                .orElseThrow(() -> new IllegalStateException("category not found"));
        products.setCategory(category);
        return products;
    }



}
