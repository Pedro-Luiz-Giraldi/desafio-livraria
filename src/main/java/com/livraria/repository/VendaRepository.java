package com.livraria.repository;

import com.livraria.model.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class VendaRepository {

    private final EntityManagerFactory emf;
    private EntityManager em;

    public VendaRepository() {
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

    // Vendas
    public void salvar(Venda venda) {
        startCon();
        em.merge(venda);
        stopCon();
    }

    public List<Venda> listarVendas() {
        startCon();
        Query query = em.createQuery("select venda from Venda venda join fetch venda.livros",
                Venda.class);
        List<Venda> vendas = query.getResultList();
        stopCon();
        return vendas;
    }

    public Long countVendas() {
        startCon();
        Long countVendas = em.createQuery("select count(venda) from Venda venda",
                        Long.class)
                .getSingleResult();
        stopCon();
        return countVendas;
    }
}
