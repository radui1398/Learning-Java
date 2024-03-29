package dao;

import entities.Database;
import entities.Movie;

import java.sql.*;
import java.util.ArrayList;

public class MovieController {
    public void create(String name,int director_id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into movies (id,name,director_id) values (?,?,?)")) {
            try (Statement stmt = con.createStatement()){
                ResultSet rs = stmt.executeQuery("select max(id) from movies");
                Integer result= rs.next() ? rs.getInt(1) : null;
                pstmt.setInt(1 ,result+1);
            }
            pstmt.setString(2 , name);
            pstmt.setInt(3 , director_id);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from movies where name like '" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findByID(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from movies where id = '"+id+"'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public ArrayList<Movie> findAll() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from movies")) {
            ArrayList<Movie>listOfMovies=new ArrayList<Movie>();
            while (rs.next()) {
                listOfMovies.add(new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
            return listOfMovies;
        }
    }
}
