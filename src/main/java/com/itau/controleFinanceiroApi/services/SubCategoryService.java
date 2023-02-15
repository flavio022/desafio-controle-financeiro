package com.itau.controleFinanceiroApi.services;


import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import com.itau.controleFinanceiroApi.dto.SubCategoryDTO;
import com.itau.controleFinanceiroApi.repositories.CategoryRepository;
import com.itau.controleFinanceiroApi.repositories.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    public SubCategoryService(
            SubCategoryRepository subCategoryRepository,
            CategoryRepository categoryRepository
    ){
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<SubCategoryDTO> listAll() {
        List<SubCategory> list = subCategoryRepository.findAll();
        return list.stream().map(x -> new SubCategoryDTO(x)).collect(Collectors.toList());
    }

    public SubCategoryDTO insert(SubCategoryDTO dto) {
        Long category_id = dto.getId_category();
        Optional<Category> categoryExist =  categoryRepository.findById(category_id);

        Category category = categoryExist.orElse(null);


        SubCategory subCategory = new SubCategory(null,dto.getNome(),category);
        subCategoryRepository.save(subCategory);

        return new SubCategoryDTO(subCategory);
    }

    public SubCategory findById(Long idCategory) {
        Optional<SubCategory> subCategoryExist = subCategoryRepository.findById(idCategory);
        SubCategory subCategory = subCategoryExist.orElse(null);
        return subCategory;
    }

    public List<SubCategory> findByCategory(Category category) {
        return subCategoryRepository.findByCategory(category);
    }
}
