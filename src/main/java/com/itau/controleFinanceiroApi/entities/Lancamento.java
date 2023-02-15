package com.itau.controleFinanceiroApi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_lancamento")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_lancamento", updatable = false, unique = true, nullable = false)
    private Long id;
    private Double valor;
    private LocalDate data;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private SubCategory subCategory;
    public Lancamento() {

    }

    public Lancamento(Long id, Double valor, LocalDate data, String comentario, SubCategory subCategory) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.comentario = comentario;
        this.subCategory = subCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}