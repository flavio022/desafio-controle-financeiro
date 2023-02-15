package com.itau.controleFinanceiroApi.dto;

import com.itau.controleFinanceiroApi.entities.Category;


public class CategoryDTO {

    private Long id;
    private String nome;

    public CategoryDTO() {

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoryDTO(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        nome = entity.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
