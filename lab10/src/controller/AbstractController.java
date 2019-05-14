package controller;

import entity.Movies;
import entity.Persons;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractController<T> {
    private EntityManagerFactory emf;

    public AbstractController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(T movact)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(movact);
        em.getTransaction().commit();
        em.close();
    }

    public List<Movies> findById(int directorId)
    {
        EntityManager em = emf.createEntityManager();
        Persons director=em.find(Persons.class,directorId);
        em.close();

        return director.getMoviesById().isEmpty() ? null : (List<Movies>) director.getMoviesById();
    }

    public Persons findByName(String personName)
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Persons> query =
                em.createNamedQuery("Person.findByName", Persons.class) .setParameter("name", personName);
        List<Persons> results = query.getResultList();
        em.close();
        return results.get(0);
    }
}
