package org.example;

import java.sql.*;
import org.h2.tools.Server;

public class Main {

    private static final String URL = "jdbc:h2:mem:moviesdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Server webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
            System.out.println("H2 Console: http://localhost:8082");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected to H2 in-memory database.");

                createTable(connection);

                insertMovie(connection, "Inception", "Christopher Nolan", 2010, 8.8);
                insertMovie(connection, "The Godfather", "Francis Ford Coppola", 1972, 9.2);
                insertMovie(connection, "Interstellar", "Christopher Nolan", 2014, 8.6);
                insertMovie(connection, "Parasite", "Bong Joon-ho", 2019, 8.5);

                System.out.println("All movies after insert:");
                printAllMovies(connection);

                updateMovieRating(connection, 1, 9.0);
                System.out.println("All movies after update:");
                printAllMovies(connection);

                System.out.println("Find by id 2:");
                findById(connection, 2);

                System.out.println("Find by director Nolan:");
                findByDirector(connection, "Christopher Nolan");

                deleteMovie(connection, 3);
                System.out.println("All movies after delete:");
                printAllMovies(connection);

                System.out.println("JDBC URL: " + URL);
                System.out.println("User: sa (no password)");
                System.out.println("Press ENTER to exit...");
                System.in.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE movies (
                id           INT AUTO_INCREMENT PRIMARY KEY,
                title        VARCHAR(150) NOT NULL,
                director     VARCHAR(100) NOT NULL,
                release_year INT NOT NULL,
                rating       DOUBLE NOT NULL
            )
            """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Table 'movies' created.");
        }
    }

    private static void insertMovie(Connection connection, String title, String director, int releaseYear, double rating) throws SQLException {
        String sql = "INSERT INTO movies (title, director, release_year, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, director);
            ps.setInt(3, releaseYear);
            ps.setDouble(4, rating);
            ps.executeUpdate();
            System.out.println("Inserted: " + title);
        }
    }

    private static void printAllMovies(Connection connection) throws SQLException {
        String sql = "SELECT id, title, director, release_year, rating FROM movies ORDER BY id";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("director") + " | " +
                                rs.getInt("release_year") + " | " +
                                rs.getDouble("rating")
                );
            }
        }
    }

    private static void findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT id, title, director, release_year, rating FROM movies WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("director") + " | " +
                                    rs.getInt("release_year") + " | " +
                                    rs.getDouble("rating")
                    );
                } else {
                    System.out.println("Movie with id " + id + " not found.");
                }
            }
        }
    }

    private static void findByDirector(Connection connection, String director) throws SQLException {
        String sql = "SELECT id, title, director, release_year, rating FROM movies WHERE director = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, director);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("director") + " | " +
                                    rs.getInt("release_year") + " | " +
                                    rs.getDouble("rating")
                    );
                }
            }
        }
    }

    private static void updateMovieRating(Connection connection, int id, double newRating) throws SQLException {
        String sql = "UPDATE movies SET rating = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newRating);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updated rating for movie id " + id);
        }
    }

    private static void deleteMovie(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted movie id " + id);
        }
    }
}