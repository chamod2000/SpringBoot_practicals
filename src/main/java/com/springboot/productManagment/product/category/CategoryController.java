package com.springboot.productManagment.product.category;

import com.springboot.productManagment.product.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {


private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategory(){
            return categoryService.getCategory();
        }

        @PostMapping(path = "/insert")
        public void insertNewCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
        }

        @DeleteMapping(path = "/delete/{id}")
        public void delectCateogry(@PathVariable("id") Long categoryId){
        categoryService.deleteCateogry(categoryId);
        }

        @PutMapping("/update/{id}")
        public Category updateCategory(@PathVariable("id") Long cid, @RequestBody Category categoryDetails){
        categoryDetails.setId(cid);
        return categoryService.update(categoryDetails);
}

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

