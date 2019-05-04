package com.company;

import dao.MovieActorController;
import dao.MovieController;
import dao.PersonController;

import entities.Actor;
import entities.Database;
import entities.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {

            //DBInitialisation initialiseDB=new DBInitialisation();

            PersonController persons = new PersonController();

            MovieController movies = new MovieController();

            MovieActorController movieActor = new MovieActorController();

            ArrayList<Movie> allMovies=movies.findAll();

            for(Movie mov : allMovies)
                System.out.println(mov.toString());

            System.out.println();


            List<Actor> allActorsByMovie=movieActor.findActors(movies.findByName("Butter In a Lordly Dish"));

            for(Actor act : allActorsByMovie)
                System.out.println(act.toString());

            System.out.println();

            List<Movie> allMoviesPlayedByActor=movieActor.findMoviesByActor(persons.findByName("Lieselotte Predovic DVM"));

            for(Movie mov : allMoviesPlayedByActor)
                System.out.println(mov.toString());

            System.out.println();

            List<Movie> allMoviesDirected=movieActor.findMoviesByDirector(persons.findByName("Irwin Blick DDS"));

            for(Movie mov : allMoviesDirected)
                System.out.println(mov.toString());


            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
