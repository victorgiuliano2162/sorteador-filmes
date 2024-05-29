package org.sorteador.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.sorteador.entities.Filme;

import java.util.List;

public abstract class FilmeDto {

    private static EntityManagerFactory emf;
    private static EntityManager em;

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
            em.getTransaction().commit();
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
            em.getTransaction().commit();
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
        try{
            return em.find(Filme.class, id);
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    //Precisa ser testado
    public List<Filme> consultarVariosPorNome(String nome){
        em.getTransaction().begin();
        try {
            //return em.createQuery("select nome from filmes where nome like '?%'").getResultList();
            String consultaNome = "select f.id, f.nome from filmes f where f.nome like :nome";
            TypedQuery<Filme> query = em.createQuery(consultaNome, Filme.class);
            query.setParameter("nome", "%"+nome+"%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Filme> consultarTodos(){
        em.getTransaction().begin();
        try{
            TypedQuery<Filme> todos = em.createQuery("select f.nome from filmes f", Filme.class);
            return todos.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    //Terminei
    public boolean alterar(int id, String nome, String quemEscolheu){
        if(consultarPorId(id) != null){
            try {
                em.getTransaction().begin();
                Filme f = em.find(Filme.class, id);
                f.setNome(nome);
                f.setQuemEscolheu(quemEscolheu);
                em.persist(f);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                em.close();
            }
        } else {
            return false;
        }
    }
}
