package com.itau.controleFinanceiroApi.services;

import com.itau.controleFinanceiroApi.dto.LancamentoDTO;
import com.itau.controleFinanceiroApi.entities.Lancamento;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import com.itau.controleFinanceiroApi.repositories.LancamentoRepository;
import com.itau.controleFinanceiroApi.repositories.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    private LancamentoRepository lancamentoRepository;
    private SubCategoryRepository subCategoryRepository;
    public LancamentoService(LancamentoRepository lancamentoRepository,SubCategoryRepository subCategoryRepository){
        this.lancamentoRepository = lancamentoRepository;
        this.subCategoryRepository = subCategoryRepository;
    }
    public List<LancamentoDTO> listAll() {
        List<Lancamento> list = lancamentoRepository.findAll();
        return list.stream().map(x -> new LancamentoDTO(x)).collect(Collectors.toList());
    }

    public LancamentoDTO insert(LancamentoDTO dto) throws Exception {
        Long id_subcategory = dto.getId_subcategory();
        Optional<SubCategory> subCategoryExist =  subCategoryRepository.findById(id_subcategory);

        SubCategory subCategory = subCategoryExist.orElse(null);
        if(subCategory==null){
            throw new Exception("A subcategoria nao existe");
        }
        LocalDate date = formtDate(dto.getData());
        Lancamento lancamento = new Lancamento(
                null,
                dto.getValor(),
                date,
                dto.getComentario(),
                subCategory
        );
        lancamentoRepository.save(lancamento);

        return new LancamentoDTO(lancamento);
    }

    private LocalDate formtDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        if(data==null){
            String dataFormatada = LocalDate.now().format(formatter);
            return LocalDate.parse(dataFormatada,formatter);
        }
        return LocalDate.parse(data,formatter);
    }
}