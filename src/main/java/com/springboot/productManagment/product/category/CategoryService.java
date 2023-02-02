package com.springboot.productManagment.product.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //get all products categories
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    //insert new category
    public void addNewCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findBycategoryName(category.getCategoryName());
        if(categoryOptional.isPresent()){
            throw new IllegalStateException("Category name already taken");
        }
        categoryRepository.save(category);
    }

    //delete category
    public void deleteCateogry(Long categoryId) {
       boolean exist =  categoryRepository.existsById(categoryId);
       if (!exist){
           throw  new IllegalStateException("ID dose not exsit");
       }
       categoryRepository.deleteById(categoryId);
    }

    //update category
    public Category update(Category categoryDetails) {
        Category category = categoryRepository.findById(categoryDetails.getId())
                .orElseThrow(()-> new IllegalStateException("Category not exist with id"));
        return categoryRepository.save(categoryDetails);

    }


//    @Transactional
//    public void updateCategory(Long categoryId, String categoryName) {
//        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalStateException("Category id dose not exist"));
//
//        if(categoryName != null && categoryName.length() > 0 &&
//        !Objects.equals(category.getCategoryName(),categoryName)){
//            Optional<Category> categoryOptional = categoryRepository.findBycategoryName(categoryName);
//            if (categoryOptional.isPresent()){
//                throw new IllegalStateException("Category Name alreay exist");
//            }
//            category.setCategoryName(categoryName);
//        }
//    }

    //update category


//    public Category updateC(Category category) {
//        return categoryRepository.save(category);
//    }
}
