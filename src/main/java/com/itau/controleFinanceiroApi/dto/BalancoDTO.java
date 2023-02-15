package com.itau.controleFinanceiroApi.dto;

import com.itau.controleFinanceiroApi.entities.Category;

public class BalancoDTO {

    private Category category;
    private Double receita;
    private Double despesas;
    private Double saldo;

    public BalancoDTO(Category category, Double receita, Double despesas, Double saldo) {
        this.category = category;
        this.receita = receita;
        this.despesas = despesas;
        this.saldo = saldo;
    }

    private BalancoDTO(){

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getReceita() {
        return receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public Double getDespesas() {
        return despesas;
    }

    public void setDespesas(Double despesas) {
        this.despesas = despesas;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
