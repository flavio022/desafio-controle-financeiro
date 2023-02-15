package com.itau.controleFinanceiroApi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_category", updatable = false, unique = true, nullable = false)
    private Long id;
    @Column(unique = true)
    private String nome;

    public Category() {

    }

    public Category(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
}