package test;

import com.github.javafaker.Faker;
import controller.AbstractController;
import entity.MovieActors;
import entity.Movies;
import entity.Persons;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class DBInitialisation {
    private EntityManagerFactory emf= Persistence.createEntityManagerFactory("MoviesPU");;
    private AbstractController<Persons> personController = new AbstractController<Persons>(emf);
    private AbstractController<Movies> moviesController = new AbstractController<Movies>(emf);
    private AbstractController<MovieActors> movieactorsController = new AbstractController<MovieActors>(emf);

    public DBInitialisation() {
        Faker faker = new Faker();

        String[] types={"director","actor","person"};

        Random rn = new Random();

        /* Generate directors */
        for (int count = 1; count <= 25; count++) {
            String name = faker.name().fullName();
            Persons pers=new Persons(name,types[0]);
            personController.create(pers);
        }

        /* Generate actors */
        for (int count = 1; count <= 50; count++) {
            String name = faker.name().fullName();
            Persons pers=new Persons(name,types[1]);
            personController.create(pers);
        }

        /* Generate normal people */
        for (int count = 1; count <= 25; count++) {
            String name = faker.name().fullName();
            Persons pers=new Persons(name,types[2]);
            personController.create(pers);
        }

        /* Generate movies*/
        for (int count = 1; count <= 25; count++) {
            EntityManager em = emf.createEntityManager();
            String title = faker.book().title();
            int director_id = Math.abs(rn.nextInt((25 - 0) + 1) + 0);
            Persons pers=em.find(Persons.class,director_id);
            Movies mov=new Movies(title,pers); em.close();
            moviesController.create(mov);

        }

        /*Generate actors*/
        for (int count = 1; count <= 25; count++) {
            for(int i=1;i<=3;i++) {
                int actor_id = 26+i;
                if(actor_id>100)
                    actor_id=actor_id-5;
                EntityManager em = emf.createEntityManager();
                Persons pers=em.find(Persons.class,actor_id);
                Movies mov=em.find(Movies.class,count);
                movieactorsController.create(new MovieActors(mov,pers));
            }
        }
    }
}
