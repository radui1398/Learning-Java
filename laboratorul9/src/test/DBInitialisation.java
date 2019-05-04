package test;

import com.github.javafaker.Faker;
import dao.MovieActorController;
import dao.MovieController;
import dao.PersonController;
import entities.Database;

import java.sql.SQLException;
import java.util.Random;

public class DBInitialisation {

    PersonController persons = new PersonController();

    MovieController movies = new MovieController();

    MovieActorController movieActor = new MovieActorController();

    public DBInitialisation() throws SQLException {
        Faker faker = new Faker();

        String[] types={"director","actor","person"};

        Random rn = new Random();

        /* Generate directors */
        for (int count = 1; count <= 25; count++) {
            String name = faker.name().fullName();
            persons.create(name,types[0]);
        }

        /* Generate actors */
        for (int count = 1; count <= 50; count++) {
            String name = faker.name().fullName();
            persons.create(name,types[1]);
        }

        /* Generate normal people */
        for (int count = 1; count <= 25; count++) {
            String name = faker.name().fullName();
            persons.create(name,types[2]);
        }

        /* Generate movies*/
        for (int count = 1; count <= 25; count++) {
            String title = faker.book().title();
            int director_id = Math.abs(rn.nextInt((25 - 0) + 1) + 0);
            movies.create(title, director_id);
        }

        /*Generate actors*/
        for (int count = 1; count <= 25; count++) {
            for(int i=1;i<=3;i++) {
                int actor_id = Math.abs(rn.nextInt((100 - 50) + 1) + 50);
                movieActor.create(count, actor_id);
            }
        }

        Database.commit();
    }
}
