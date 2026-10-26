package com.snakearena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/snakearena";
        String user = "root";
        String password = "Root@123";

        try (Connection con =
                 DriverManager.getConnection(url, user, password)) {

            System.out.println("MySQL Connected Successfully!");
            System.out.println("Database: " +
                    con.getCatalog());

        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}
