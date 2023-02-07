package com.springboot.productManagment.product.controller;

import com.springboot.productManagment.product.model.CategoryDTO;
import com.springboot.productManagment.product.model.ProductsDTO;
import com.springboot.productManagment.product.service.CategoryService;
import com.springboot.productManagment.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

  @Autowired
private CategoryService categoryService;

        @Operation(summary = "This is to fetch data selected id in database")
        @GetMapping(path = "/read/{id}")
        public CategoryDTO getCategory(@PathVariable("id") Long id){
          CategoryDTO searchCategory = categoryService.getCategoryByID(id);
            return searchCategory;
        }

        @Operation(summary = "This is to fetch all the categories stored in database")
        @GetMapping(path = "/read/all")
        public List<CategoryDTO> getAllCategory(){
        return categoryService.gettAllCategory();
        }

        @Operation(summary = "This is to fetch all data by pagination")
        @GetMapping(path = "/read/pagination/{offset}/{pageSize}")
        public List<CategoryDTO> getProductWithPagination (@PathVariable Integer offset, @PathVariable Integer pageSize){
        return  categoryService.findproductWithPagination(offset,pageSize);
    }

        @Operation(summary = "This is insert new category into database")
        @PostMapping
        public Long insertNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.addNewCategory(categoryDTO);
        }

        @Operation(summary = "This is delete existing category from database")
        @DeleteMapping(path = "/{id}")
        public void delectCateogry(@PathVariable("id") Long categoryId){
         categoryService.deleteCateogry(categoryId);
        }

        @Operation(summary = "This is update registered category from database")
        @PutMapping("/{id}")
        public CategoryDTO updateCategory(@PathVariable("id") Long cid, @RequestBody CategoryDTO categoryDTO) {
            categoryDTO.setId(cid);
            return categoryService.update(cid, categoryDTO);
        }


    //        @PostMapping(path = "/insert")
//        public void insertNewCategory(@RequestBody Category category){
//        categoryService.addNewCategory(category);
//        }


//        @PutMapping("/{id}")
//        public Category updateCategory(@PathVariable("id") Long cid, @RequestBody Category categoryDetails){
//        categoryDetails.setId(cid);
//        return categoryService.update(categoryDetails);


//        @PutMapping(path = "/update/{id}")
//        public void updateCategory(
//                @PathVariable("id") Long categoryId,
//                @RequestParam(required = false) String categoryName){
//        categoryService.updateCategory(categoryId,categoryName);
//        }

//            @PutMapping(path = "/update/{id}")
//            public Category update(@PathVariable("id") Long cid,@RequestBody Category category){
//                category.setId(cid);
//                return categoryService.updateC(category);
//            }

}

