package com.livraria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Eletronico extends Livro {

    @Column(nullable = false)
    protected Integer tamanho;

    public Eletronico() {
    }

    public Eletronico(Impresso i) {
        this.titulo = i.getTitulo();
        this.autores = i.getAutores();
        this.editora = i.getEditora();
        this.preco = i.getPreco();
    }

    public Eletronico(Eletronico e) {
        this.titulo = e.getTitulo();
        this.autores = e.getAutores();
        this.editora = e.getEditora();
        this.preco = e.getPreco();
        this.tamanho = e.getTamanho();
    }

    public Eletronico(Long id, String titulo, String autores, String editora, Double preco, Integer tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Eletronico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", editora='" + editora + '\'' +
                ", preco=" + preco +
                ", tamanho=" + tamanho +
                '}';
    }
}
