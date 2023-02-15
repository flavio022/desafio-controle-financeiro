package com.itau.controleFinanceiroApi.dto;

import com.itau.controleFinanceiroApi.entities.Lancamento;

public class LancamentoDTO {

    private Long id;
    private Double valor;
    private String data;
    private String comentario;
    private Long id_subcategory;

    public LancamentoDTO() {

    }

    public LancamentoDTO(Long id, Double valor, String data,String comentario,Long id_subcategory) {
        super();
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.comentario = comentario;
        this.id_subcategory = id_subcategory;
    }

    public LancamentoDTO(Lancamento entity) {
        id = entity.getId();
        valor = entity.getValor();
        data = entity.getData().toString();
        comentario = entity.getComentario();
        id_subcategory = entity.getSubCategory().getId();
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getComentario() {
        return comentario;
    }

    public Long getId_subcategory() {
        return id_subcategory;
    }
}
