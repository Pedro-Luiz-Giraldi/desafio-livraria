package com.livraria.repository;

import com.livraria.model.Eletronico;
import com.livraria.model.Impresso;
import com.livraria.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class LivroRepository {

    private final EntityManagerFactory emf;
    private EntityManager em;

    public LivroRepository() {
        this.emf = Persistence.createEntityManagerFactory("livraria-virtual");
    }

    private void startCon() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    private void stopCon() {
        em.getTransaction().commit();
        em.close();
    }

    public void salvar(Livro livro) {
        startCon();
        em.merge(livro);
        stopCon();
    }

    // Livros Impressos
    public List<Impresso> listarImpressos() {
        startCon();
        Query query = em.createQuery("select impresso from Impresso impresso");
        List<Impresso> impressos = query.getResultList();
        stopCon();

        return impressos;
    }

    public Long countImpressos() {
        startCon();
        Long countLivros = em.createQuery("select count(impresso) from Impresso impresso",
                Long.class)
                .getSingleResult();
        stopCon();
        return countLivros;
    }

    // Livros Eletronicos
    public List<Eletronico> listarEletronicos() {
        startCon();
        Query query = em.createQuery("select eletronico from Eletronico eletronico");
        List<Eletronico> eletronicos = query.getResultList();
        stopCon();

        return eletronicos;
    }

    public Long countEletronicos() {
        startCon();
        Long countLivros = em.createQuery("select count(eletronico) from Eletronico eletronico",
                        Long.class)
                .getSingleResult();
        stopCon();
        return countLivros;
    }

}
