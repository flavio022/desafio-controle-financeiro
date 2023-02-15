package com.itau.controleFinanceiroApi.services;

import com.itau.controleFinanceiroApi.dto.CategoryDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryDTO> listAll() {
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public Category insert(CategoryDTO dto) {
        Category category = new Category(null,dto.getNome());
        categoryRepository.save(category);
        return category;
    }

    public Category findById(Long idCategory) {
        Optional<Category> categoryExist = categoryRepository.findById(idCategory);
        Category category = categoryExist.orElse(null);
        return category;
    }
}
