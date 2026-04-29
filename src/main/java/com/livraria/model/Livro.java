package com.livraria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String titulo;

    @Column(nullable = false)
    protected String autores;

    @Column(nullable = false)
    protected String editora;

    @Column(nullable = false)
    protected Double preco;

    public Livro() {
    }

    public Livro(String titulo, String autores, String editora, Double preco) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
