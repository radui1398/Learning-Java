package dao;

import entities.Database;

import java.sql.*;

public class PersonController {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into persons (id,name) values (?,?)")) {
            try (Statement stmt = con.createStatement()){
                ResultSet rs = stmt.executeQuery("select max(id) from persons");
                Integer result= rs.next() ? rs.getInt(1) : null;
                pstmt.setInt(1 ,result+1);
            }
            pstmt.setString(2 , name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from persons where name like '" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findByID(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from persons where id = '"+id+"'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}