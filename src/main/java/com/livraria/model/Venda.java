package com.livraria.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @Column(nullable = false)
    private String cliente;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "venda_livros",
            joinColumns = @JoinColumn(name = "numero_venda"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> livros = new ArrayList<>();

    @Column(nullable = false)
    private Double valor;

    public Venda() {
    }

    public Venda(String cliente, List<Livro> livros) {
        this.cliente = cliente;
        this.livros = livros;
        this.valor = livros.stream()
                .mapToDouble(Livro::getPreco)
                .sum();
        livros.add(1, new Impresso());
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void listarLivros() {
        for (Livro l : this.livros) {
            System.out.println(l.toString());
        }
    }


    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "numero=" + numero +
                ", cliente='" + cliente + '\'' +
                ", valor=" + valor +
                ", livros=\n" + livros.stream()
                                    .map(Objects::toString)
                                    .collect(Collectors.joining(", \n")) +
                '}';
    }
}