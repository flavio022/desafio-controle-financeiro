package com.itau.controleFinanceiroApi.services;


import com.itau.controleFinanceiroApi.dto.BalancoDTO;
import com.itau.controleFinanceiroApi.dto.CategoryDTO;
import com.itau.controleFinanceiroApi.dto.LancamentoDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.Lancamento;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import com.itau.controleFinanceiroApi.repositories.CategoryRepository;
import com.itau.controleFinanceiroApi.repositories.LancamentoRepository;
import com.itau.controleFinanceiroApi.repositories.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalancoServiceTest {

    @Mock
    public LancamentoRepository lancamentoRepository;
    @Mock
    public SubCategoryRepository subCategoryRepository;
    @Mock
    public CategoryRepository categoryRepository;
    @InjectMocks
    private BalancoService balancoService;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private SubCategory subCategory;
    private Lancamento lancamento;
    private LancamentoDTO lancamentoDTO;

    @BeforeEach
    public void setup() {
        category = new Category(1l,"Refeicao");
        subCategory= new SubCategory(1l,"Almoço",category);
        lancamento = new Lancamento(1l,40.00, LocalDate.now(),"Almoço no bar",subCategory);
        lancamentoDTO = new LancamentoDTO(1l,40.00,"15/02/2023","Almoço no bar",1l);
    }


    @Test
    void shouldBeReturnABalance(){

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(subCategoryRepository.findByCategory(any())).thenReturn(List.of(subCategory));
        when(lancamentoRepository.findByCatAnDate(any(),any(),any())).thenReturn(List.of(lancamento));
        List<BalancoDTO> response = balancoService.listAll(LocalDate.now(),LocalDate.now(),1l );
        assertNotNull(response);

    }

}

