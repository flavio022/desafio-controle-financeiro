package com.itau.controleFinanceiroApi.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tb_subcategory")
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_subcategory", updatable = false, unique = true, nullable = false)
    private Long id;
    @Column(unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public SubCategory() {

    }


    public SubCategory(Long id, String nome, Category category) {
        this.id = id;
        this.nome = nome;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}