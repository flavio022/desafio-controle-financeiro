package com.itau.controleFinanceiroApi.dto;

import com.itau.controleFinanceiroApi.entities.SubCategory;

public class SubCategoryDTO {

    private Long id;
    private String nome;

    private Long id_category;

    public SubCategoryDTO() {

    }
    public String getNome() {
        return nome;
    }


    public SubCategoryDTO(Long id, String nome,Long id_category) {
        super();
        this.id = id;
        this.nome = nome;
        this.id_category = id_category;
    }

    public SubCategoryDTO(SubCategory entity) {
        id = entity.getId();
        nome = entity.getNome();
        id_category = entity.getCategory().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_category() {
        return id_category;
    }
}
