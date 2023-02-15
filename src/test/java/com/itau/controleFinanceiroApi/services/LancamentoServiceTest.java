package com.itau.controleFinanceiroApi.services;
import com.itau.controleFinanceiroApi.dto.LancamentoDTO;
import com.itau.controleFinanceiroApi.dto.SubCategoryDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.Lancamento;
import com.itau.controleFinanceiroApi.entities.SubCategory;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LancamentoServiceTest {


    @Mock
    public SubCategoryRepository subCategoryRepository;

    @Mock
    public LancamentoRepository lancamentoRepository;
    @InjectMocks
    private LancamentoService lancamentoService;
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
    void shouldSaveLancamento() throws Exception {
        when(lancamentoRepository.save(any())).thenReturn(lancamento);
        when(subCategoryRepository.findById(anyLong())).thenReturn(Optional.of(subCategory));

        final var response = lancamentoService.insert(lancamentoDTO);

        assertNotNull(response);

    }

    @Test
    public void shouldReturnAllLancamentos(){
        when(lancamentoRepository.findAll()).thenReturn(List.of(lancamento));
        List<LancamentoDTO> response = lancamentoService.listAll();
        assertNotNull(response);

        assertEquals(1,response.size());

        assertEquals(LancamentoDTO.class,response.get(0).getClass());

        assertEquals(1l,response.get(0).getId());

    }

}
