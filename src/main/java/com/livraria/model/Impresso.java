package com.livraria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Impresso extends Livro {

    @Column(nullable = false)
    protected Double frete;

    @Column(nullable = false)
    protected Integer estoque;

    public Impresso() {
    }

    public Impresso(String titulo, String autores, String editora, Double preco, Double frete, Integer estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public void atualizarEstoque() {
        this.estoque--;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Impresso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", editora='" + editora + '\'' +
                ", preco=" + preco +
                ", frete=" + frete +
                ", estoque=" + estoque +
                '}';
    }
}
