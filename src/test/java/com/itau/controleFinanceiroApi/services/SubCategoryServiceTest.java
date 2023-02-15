package com.itau.controleFinanceiroApi.services;

import com.itau.controleFinanceiroApi.dto.SubCategoryDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import com.itau.controleFinanceiroApi.repositories.CategoryRepository;
import com.itau.controleFinanceiroApi.repositories.SubCategoryRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubCategoryServiceTest {


    @Mock
    public SubCategoryRepository subCategoryRepository;

    @Mock
    public CategoryRepository categoryRepository;
    @InjectMocks
    private SubCategoryService subCategoryService;
    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private SubCategory subCategory;
    private SubCategoryDTO subCategoryDTO;
    @BeforeEach
    public void setup() {
        category = new Category(1l,"Refeicao");
        subCategory= new SubCategory(1l,"Almoço",category);
        subCategoryDTO = new SubCategoryDTO(1l,"Almoço",1l);
    }
    @Test
    void shouldSaveASubCategory() throws Exception {
        when(subCategoryRepository.save(any())).thenReturn(subCategory);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        final var response = subCategoryService.insert(subCategoryDTO);

        assertNotNull(response);
        assertEquals("Almoço", response.getNome());

    }


    @Test
    public void givenASubCategoryIdShouldReturnASubCategory(){
        final var category = new Category(1l,"Refeicao");
        final var subCategory = new SubCategory(1l,"Almoço",category);

        when(subCategoryRepository.findById(anyLong())).thenReturn(Optional.of(subCategory));
        final var actual = subCategoryService.findById(1l);
        assertThat(actual).usingRecursiveComparison().isEqualTo(subCategory);
    }

    @Test
    public void shouldReturnAllSubCategories(){
        when(subCategoryRepository.findAll()).thenReturn(List.of(subCategory));
        List<SubCategoryDTO> response = subCategoryService.listAll();
        assertNotNull(response);

        assertEquals(1,response.size());

        assertEquals(SubCategoryDTO.class,response.get(0).getClass());

        assertEquals(1l,response.get(0).getId());

    }
}
