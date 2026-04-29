package com.livraria;

import com.livraria.model.Eletronico;
import com.livraria.model.Impresso;
import com.livraria.model.Livro;
import com.livraria.model.Venda;
import com.livraria.repository.LivroRepository;
import com.livraria.repository.VendaRepository;

import java.util.*;

public class LivrariaVirtual {

    private LivroRepository livroRepository = new LivroRepository();
    private VendaRepository vendaRepository = new VendaRepository();

    private Integer MAX_IMPRESSOS = 10;
    private Integer MAX_ELETRONICOS = 20;
    private Integer MAX_VENDAS = 50;

    // listagem
    public List<Impresso> listarImpressos() { return livroRepository.listarImpressos(); }

    public List<Eletronico> listarEletronicos() { return livroRepository.listarEletronicos(); }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        livros.addAll(livroRepository.listarImpressos());
        livros.addAll(livroRepository.listarEletronicos());
        return livros;
    }

    public List<Venda> listarVendas() { return vendaRepository.listarVendas(); }

    // cadastro
    public void cadastrarLivro(Livro livro) { livroRepository.salvar(livro); }

    public void cadastrarVenda(Venda venda) { vendaRepository.salvar(venda); }

    // count
    public Long numImpressos() { return livroRepository.countImpressos(); }
    public Long numEletronicos() { return livroRepository.countEletronicos(); }
    public Long numVendas() { return vendaRepository.countVendas(); }

    public void preencherDadosLivro(Livro livro, Scanner scanner) {
        System.out.println("Titulo: ");
        livro.setTitulo(scanner.next());
        System.out.println("Autores: ");
        livro.setAutores(scanner.next());
        System.out.println("Editora: ");
        livro.setEditora(scanner.next());
        System.out.println("Preco: ");
        livro.setPreco(scanner.nextDouble());
    }

    public static void main(String[] args) {


        LivrariaVirtual livraria = new LivrariaVirtual();
        Scanner scanner = new Scanner(System.in);

        boolean sair = false;
        do {
            System.out.println("""
                    ==========================
                    |    LIVRARIA VIRTUAL    |
                    ==========================
                    | 1 - Cadastrar Livro    |
                    | 2 - Realizar Venda     |
                    | 3 - Listar Livros      |
                    | 4 - Listar Impressos   |
                    | 5 - Listar Eletronicos |
                    | 6 - Listar Vendas      |
                    | 7 - Sair               |
                    ==========================
                    """);
            Integer operacao = scanner.nextInt();

            switch (operacao) {
                case 1:
                    System.out.println("""
                            ==========================
                            | 1 - Livro Impresso     |
                            | 2 - Livro Eletronico   |
                            | 3 - Ambos              |
                            ==========================
                            """);
                    Integer tipo = scanner.nextInt();

                    switch (tipo){
                        case 1:
                            if ((livraria.numImpressos() + 1) > livraria.MAX_IMPRESSOS) {
                                System.out.println("Limite de Impressos atingido!");
                                break;
                            }
                            Impresso impresso = new Impresso();
                            livraria.preencherDadosLivro(impresso, scanner);

                            System.out.println("Frete: ");
                            impresso.setFrete(scanner.nextDouble());
                            System.out.println("Estoque: ");
                            impresso.setEstoque(scanner.nextInt());

                            livraria.cadastrarLivro(impresso);
                            break;
                        case 2:
                            if ((livraria.numEletronicos() + 1) > livraria.MAX_ELETRONICOS) {
                                System.out.println("Limite de Eletronicos atingido!");
                                break;
                            }
                            Eletronico eletronico = new Eletronico();
                            livraria.preencherDadosLivro(eletronico, scanner);

                            System.out.println("Tamanho: ");
                            eletronico.setTamanho(scanner.nextInt());

                            livraria.cadastrarLivro(eletronico);
                            break;
                        case 3:
                            if ((livraria.numImpressos() + 1) > livraria.MAX_IMPRESSOS) {
                                System.out.println("Limite de Impressos atingido!");
                                break;
                            } else if ((livraria.numEletronicos() + 1) > livraria.MAX_ELETRONICOS) {
                                System.out.println("Limite de Eletronicos atingido!");
                                break;
                            } else if (((livraria.numImpressos() + 1) > livraria.MAX_IMPRESSOS) &&
                                    ((livraria.numEletronicos() + 1) > livraria.MAX_ELETRONICOS)) {
                                System.out.println("Limite de Impressos e Eletronicos atingido!");
                                break;
                            }

                            Impresso impressoCadastro = new Impresso();
                            livraria.preencherDadosLivro(impressoCadastro, scanner);
                            System.out.println("Frete: ");
                            impressoCadastro.setFrete(scanner.nextDouble());
                            System.out.println("Estoque: ");
                            impressoCadastro.setEstoque(scanner.nextInt());

                            livraria.cadastrarLivro(impressoCadastro);

                            Eletronico eletronicoCadastro = new Eletronico(impressoCadastro);
                            System.out.println("Tamanho: ");
                            eletronicoCadastro.setTamanho(scanner.nextInt());

                            livraria.cadastrarLivro(eletronicoCadastro);
                            break;
                    }
                    break;
                case 2:
                    if ((livraria.numVendas() + 1) > livraria.MAX_VENDAS) {
                        System.out.println("Limite de Vendas atingido!");
                        break;
                    }

                    Venda vendaParaCadastro = new Venda();

                    System.out.println("Cliente:");
                    vendaParaCadastro.setCliente(scanner.next());

                    System.out.println("Quantidade de livros:");
                    Integer qtdLivros = scanner.nextInt();
                    List<Livro> livros = new ArrayList<>();
                    for (int i = 0; i < qtdLivros; i++) {
                        System.out.println("""
                            ==========================
                            | 1 - Livro Impresso     |
                            | 2 - Livro Eletronico   |
                            ==========================
                            """);
                        Integer opt = scanner.nextInt();

                        switch (opt) {
                            case 1:
                                List<Impresso> livrosImpressos = new ArrayList<>(livraria.listarImpressos());
                                System.out.println("=============================================");
                                for (Impresso impresso : livrosImpressos) {
                                    System.out.println(impresso.toString());
                                    System.out.println("=============================================");
                                }
                                System.out.println("Id do Livro:");
                                Long idLivro = scanner.nextLong();

                                Impresso impressoVenda = livrosImpressos.stream()
                                        .filter(livro -> livro.getId().equals(idLivro))
                                        .findFirst().orElse(null);

                                // atualizarEstoque
                                if (livros.contains(impressoVenda)) {

                                }
                                if (impressoVenda.getEstoque() > 0) {
                                    impressoVenda.atualizarEstoque();
                                    //TODO
                                    if (livros.contains(impressoVenda)) {
                                        impressoVenda.atualizarEstoque();
                                    } else {
                                        livros.add(impressoVenda);
                                    }
                                } else {
                                    System.out.println("Livro sem estoque!");
                                    break;
                                }

                                break;
                            case 2:
                                List<Eletronico> livrosEletronicos = new ArrayList<>(livraria.listarEletronicos());
                                System.out.println("=============================================");
                                for (Eletronico eletronico : livrosEletronicos) {
                                    System.out.println(eletronico.toString());
                                    System.out.println("=============================================");
                                }
                                System.out.println("Id do Livro:");
                                Long idLivroEletronico = scanner.nextLong();

                                Eletronico eletronicoId = livrosEletronicos.stream()
                                        .filter(livro -> livro.getId().equals(idLivroEletronico))
                                        .findFirst().orElse(null);
                                if (livros.contains(eletronicoId) && eletronicoId != null) {
                                    livros.add(new Eletronico(eletronicoId));
                                } else {
                                    livros.add(eletronicoId);
                                }
                                break;
                        }
                    }
                    vendaParaCadastro.setLivros(livros);
                    vendaParaCadastro.setValor(livros.stream()
                            .mapToDouble(l -> {
                                double preco = l.getPreco();
                                double frete = (l instanceof Impresso i) ? i.getFrete() : 0.0;
                                return preco + frete;
                            })
                            .sum());

                    livraria.cadastrarVenda(vendaParaCadastro);
                    break;
                case 3:
                    System.out.println("=============================================");
                    for (Livro livro : livraria.listarLivros()) {
                        System.out.println(livro.toString());
                        System.out.println("=============================================");
                    }
                    break;
                case 4:
                    System.out.println("=============================================");
                    for (Impresso impresso : livraria.listarImpressos()) {
                        System.out.println(impresso.toString());
                        System.out.println("=============================================");
                    }
                    break;
                case 5:
                    System.out.println("=============================================");
                    for (Eletronico eletronico : livraria.listarEletronicos()) {
                        System.out.println(eletronico.toString());
                        System.out.println("=============================================");
                    }
                    break;
                case 6:
                    System.out.println("=============================================");
                    for (Venda venda : livraria.listarVendas()) {
                        System.out.println(venda.toString());
                        System.out.println("=============================================");
                    }
                    break;
                case 7:
                    sair = true;
                    break;
            }

        } while (!sair);
    }
}