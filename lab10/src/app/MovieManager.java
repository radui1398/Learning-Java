package app;

import controller.AbstractController;
import entity.MovieActors;
import entity.Movies;
import entity.Persons;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class MovieManager
{
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviesPU");
    private AbstractController<Persons> personController = new AbstractController<Persons>(emf);
    private AbstractController<Movies> moviesController = new AbstractController<Movies>(emf);
    private AbstractController<MovieActors> movieactorsController = new AbstractController<MovieActors>(emf);

    Scanner scanner = new Scanner(System.in);

    public void run()
    {
        while (true)
        {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            if (command.equals("exit"))
            {
                break;
            }

            String[] params = command.trim().split("\\s+");
            switch (params[0])
            {
                case "create-person":
                    createPerson(params[1]); //the person name
                    break;
                case "find-person":
                    findPerson(); //the person name
                    break;
                case "create-movie":
                    createMovie(params[1], Integer.parseInt(params[2])); //the album name and the director
                    break;
                case "list-movies":
                    listMovies(Integer.parseInt(params[1])); //the director ID
                    break;
                case "add-actors":
                    addActors();
                    break;
                case "list-actors-from-movie":
                    listActors();
                    break;
                case "list-movies-from-actor":
                    listMoviesPlayedByActor();
                    break;
                case "list-movies-from-director":
                    listMovieDirectedBy();
            }
        }
    }

    private void listMovieDirectedBy() {
        System.out.println("Introduceti ID-ul directorului:");
        String directorId = scanner.nextLine();
        EntityManager em = emf.createEntityManager();
        Persons pers = em.find(Persons.class,Integer.parseInt(directorId));
        List<Movies> movieList = pers.getMoviesById();
        for(Movies movies:movieList)
            System.out.println(movies);
    }

    private void listMoviesPlayedByActor() {
        System.out.println("Introduceti ID-ul actorului:");
        String actorId = scanner.nextLine();
        EntityManager em = emf.createEntityManager();
        Persons pers = em.find(Persons.class,Integer.parseInt(actorId));
        List<MovieActors> movieList = pers.getMovieActorsById();
        for(MovieActors actors:movieList)
            System.out.println(actors.getMoviesByMovieId());
    }

    private void listActors() {
        System.out.println("Introduceti ID-ul filmului:");
        String movieId = scanner.nextLine();
        EntityManager em = emf.createEntityManager();
        Movies mov = em.find(Movies.class,Integer.parseInt(movieId));
        List<MovieActors> movieList = mov.getMovieActorsById();
        for(MovieActors actors:movieList)
            System.out.println(actors.getPersonsByActorId());

    }

    private void addActors() {
        System.out.println("Introduceti id-ul filmului:");
        String movieId = scanner.nextLine();
        EntityManager em = emf.createEntityManager();
        Movies mov = em.find(Movies.class,Integer.parseInt(movieId));
        System.out.println("Introduceti id-ul actorului:");
        String actorId = scanner.nextLine();
        Persons pers = em.find(Persons.class,Integer.parseInt(actorId));
        movieactorsController.create(new MovieActors(mov,pers));

    }

    private void createPerson(String personName)
    {
        Persons person = new Persons(personName);
        personController.create(person);
    }

    private void findPerson()
    {
        System.out.println("Introduceti numele persoanei cautate:");
        String persName = scanner.nextLine();
        System.out.println(personController.findByName(persName));
    }

    private void createMovie(String movieName, int directorId)
    {
        EntityManager em = emf.createEntityManager();
        Persons director = em.find(Persons.class,directorId);
        Movies movie = new Movies(movieName,director);
        moviesController.create(movie);
    }

    private void listMovies(int directorId)
    {
        List<Movies> movieList = moviesController.findById(directorId);
        for(Movies mov:movieList)
            System.out.println(mov);
    }
}