package com.company;

import dao.MovieController;
import dao.PersonController;
import entities.Database;

import java.awt.print.PrinterException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            PersonController persons = new PersonController();

            MovieController movies = new MovieController();

            persons.create("Francis Ford Coppola");
            persons.create("Marlon Brando");
            persons.create("Al Pacino");

            //Database.commit();

            System.out.println(persons.findByName("Marlon Brando"));
            System.out.println(persons.findByID(1));

            movies.create("The Godfather", persons.findByName("Francis Ford Coppola"));
            Database.commit();

            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
