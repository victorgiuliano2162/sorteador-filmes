package org.sorteador.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilmeDto {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    List<Filme> filme = new ArrayList<>();

    static {
        try {
            emf = Persistence.createEntityManagerFactory("sorteador-filmes");
            em = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrar(Filme filme){
        em.getTransaction().begin();
        try {
            em.persist(filme);
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean excluir(int id){
        em.getTransaction().begin();
        try {
            em.remove(em.find(Filme.class, id));
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Filme consultarPorId(int id){
        em.getTransaction().begin();
        return em.find(Filme.class, id);
    }

    //Precisa ser testado
    public List<Objects> consultarVariosPorNome(String nome){
        em.getTransaction().begin();
        String jpql = "select name from Filme where nome like u";
        TypedQuery<Objects> query = em.createQuery(jpql, Objects.class);

        return query.getResultList();
    }

    public List<Filme> consultarTodos(){
        em.getTransaction().begin();
        return em.createQuery("select from * Filme", Filme.class).getResultList();
    }

    public void alterar(String n){
        em.getTransaction().begin();
        //em.find(Filme.class, f.getId());
        em.getTransaction().commit();
    }
}
