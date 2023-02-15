package com.itau.controleFinanceiroApi.services;


import com.itau.controleFinanceiroApi.controllers.LancamentoController;
import com.itau.controleFinanceiroApi.dto.BalancoDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.Lancamento;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import com.itau.controleFinanceiroApi.repositories.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Service
public class BalancoService {

    private LancamentoRepository lancamentoRepository;
    private SubCategoryService subCategoryService;
    private CategoryService categoryService;
    Logger logger = Logger.getLogger(LancamentoController.class.getName());


    public BalancoService(
            LancamentoRepository lancamentoRepository,
            SubCategoryService subCategoryService,
            CategoryService categoryService){

        this.lancamentoRepository = lancamentoRepository;
        this.subCategoryService = subCategoryService;
        this.categoryService = categoryService;
    }
    public List<BalancoDTO> listAll(LocalDate dateFim,
                                    LocalDate dateIni,
                                    Long id_subcategory) {

        Category category = categoryService.findById(id_subcategory);
        List<SubCategory> subCategory =  subCategoryService.findByCategory(category);
        List<Lancamento> lancamentos = lancamentoRepository.findByCatAnDate(dateFim, dateIni,subCategory );

        Double receita = getReceita(lancamentos);
        Double despesas = getDespesas(lancamentos);

        Double saldo = getSaldo(receita,despesas);
        BalancoDTO balancoDTO = new BalancoDTO(category,receita,despesas,saldo);

        return Arrays.asList(balancoDTO);
    }

    public Double getDespesas(List<Lancamento> lancamentos){

        Double despesas  = lancamentos.stream().filter(
                        user ->user.getValor()<0)
                .mapToDouble(user ->user.getValor()).sum();
        return despesas;
    }

    public Double getReceita(List<Lancamento> lancamentos){

        Double receita  = lancamentos.stream().filter(
                        user ->user.getValor()>0)
                .mapToDouble(user ->user.getValor()).sum();
        return receita;
    }
    public Double getSaldo(Double receita,Double despesas){
        Double saldo = receita - Math.abs(despesas);
        return saldo;
    }


}
