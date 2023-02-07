package com.springboot.productManagment.product.service;
import com.springboot.productManagment.product.domain.Category;
import com.springboot.productManagment.product.domain.Products;
import com.springboot.productManagment.product.model.CategoryDTO;
import com.springboot.productManagment.product.model.ProductsDTO;
import com.springboot.productManagment.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;


    //get all products categories
    public List<CategoryDTO> gettAllCategory() {
        final List<Category> getAllCategory =categoryRepository.findAll();
        return getAllCategory.stream()
                .map((category) -> maptoDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());

    }

    public List<CategoryDTO> findproductWithPagination(Integer offset, Integer pageSize) {

        final Page<Category> getAllproducts = categoryRepository.findAll(PageRequest.of(offset,pageSize));
        return getAllproducts.stream()
                .map((category) -> maptoDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryByID(Long id) {
        if(categoryRepository.existsById(id)){
            return categoryRepository.findById(id).map(category ->maptoDTO(category,new CategoryDTO()))
                    .orElseThrow(() -> new IllegalStateException("not found id"));
        }else {
            return null;
        }
    }

    public Long addNewCategory(CategoryDTO  categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO,category);
       return categoryRepository.save(category).getId();
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
    public CategoryDTO update(Long cid,CategoryDTO categoryDTO) {
        final Category category =categoryRepository.findById(cid).orElseThrow(() -> new IllegalStateException("not found id"));
        mapToEntity(categoryDTO,category);
        categoryRepository.save(category);
        return categoryDTO;
    }



    private CategoryDTO maptoDTO(final Category category,final CategoryDTO categoryDTO){
        categoryDTO.setId(category.getId());
        categoryDTO.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category){
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }

    //insert new category
//    public void addNewCategory(Category category) {
//        Optional<Category> categoryOptional = categoryRepository.findBycategoryName(category.getCategoryName());
//        if(categoryOptional.isPresent()){
//            throw new IllegalStateException("Category name already taken");
//        }
//        categoryRepository.save(category);
//    }


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
