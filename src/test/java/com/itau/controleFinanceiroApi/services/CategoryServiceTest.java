package com.itau.controleFinanceiroApi.services;

import com.itau.controleFinanceiroApi.dto.CategoryDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    public CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    private Category category;


    @BeforeEach
    public void setup() {
        category = new Category(1l,"Refeicao");
    }

    @Test
    void shouldSaveOnCategory() {

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        final var newCategory = categoryService.insert(new CategoryDTO(1l,"Refeicao"));

        assertThat(newCategory.getNome()).isEqualTo(category.getNome());

    }


    @Test
    public void givenACategoryIdShouldReturnACategory(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        final var newCategory = categoryService.findById(1l);
        assertThat(newCategory).usingRecursiveComparison().isEqualTo(category);
    }

    @Test
    public void shouldReturnAllCategories(){
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        List<CategoryDTO> response = categoryService.listAll();
        assertNotNull(response);

        assertEquals(1,response.size());

        assertEquals(CategoryDTO.class,response.get(0).getClass());

        assertEquals(1l,response.get(0).getId());

    }

}

