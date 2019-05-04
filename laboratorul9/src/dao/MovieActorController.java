package dao;

import entities.Actor;
import entities.Database;
import entities.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieActorController {
    public void create(int movie_id,int actor_id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into movie_actors (movie_id,actor_id) values (?,?)")) {
            try (Statement stmt = con.createStatement()){
                pstmt.setInt(1 ,movie_id);
            }
            pstmt.setInt(2 , actor_id);
            pstmt.executeUpdate();
        }
    }
    public List<Actor> findActors(int id_movie) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from movie_actors where movie_id = '"+id_movie+"'")) {
            List<Actor>listOfActors=new ArrayList<Actor>();
            while (rs.next()) {
                ResultSet rs1;
                try (Statement stmt1 = con.createStatement()) {
                    rs1 = stmt1.executeQuery("select * from persons where id = '" + rs.getInt(2) + "'");
                    if(rs1.next()) listOfActors.add(new Actor(rs1.getInt(1),rs1.getString(2)));
                }
            }
            return listOfActors;
        }
    }

    public List<Movie> findMoviesByActor(int id_actor) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from movie_actors where actor_id = '"+id_actor+"'")) {
            List<Movie>listOfMovies=new ArrayList<Movie>();
            while (rs.next()) {
                ResultSet rs1;
                try (Statement stmt1 = con.createStatement()) {
                    rs1 = stmt1.executeQuery("select * from movies where id = '" + rs.getInt(1) + "'");
                    if(rs1.next()) listOfMovies.add(new Movie(rs1.getInt(1),rs1.getString(2),rs1.getInt(3)));
                }
            }
            return listOfMovies;
        }
    }

    public List<Movie> findMoviesByDirector(int id_director) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select distinct m.id,m.name,m.director_id from movie_actors ma join movies m on m.id=ma.movie_id where m.director_id = '" + id_director + "'")) {
            List<Movie> listOfMovies = new ArrayList<Movie>();
            while (rs.next())
                listOfMovies.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            return listOfMovies;
        }
    }
}
