package sda.jdbc.practical;


import lombok.SneakyThrows;

import java.sql.*;

public class PracticalMain {
    private static final String urlDB = "jdbc:mysql://localhost:3306/sda_database";
    private static final String userNameDB = "root";
    private static final String userPasswordDB = "1990_Bracaraugustanorum";

    public static void task1() {
         /*
    Using the JDBC API and any relational database (e.g. H2) make the following queries:

    - create a table MOVIES with columns: id of type INTEGER AUTO INCREMENT,title of type VARCHAR (255), genre of type VARCHAR (255),
    yearOfRelease of type INTEGER. Note that a table named MOVIE may already exist. In that case, delete it.
    - add any three records to the MOVIES table
    - update one selected record (use the PreparedStatement)
    - delete selected record with specified id
    - display all other records in the database

    In the task, focus on the correct use of the JDBC API. All queries can be made directly in the main method. Use a single connection
    to execute all queries. However, remember to use try-with-resources when opening a connection and creating objects such asStatement
    or PreparedStatement. Also, don't worry about exception handling in this task (in case of error, display stacktrace).
     */
//        deleteMovieTable();
//        createMovieTable();
//        insertMoviesIntoTable();
//        updateRecord(3,"Thriller");
//        deleteRecord(1);
        displayAllRecords();
    }

    public static void createMovieTable() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String tableSql = "CREATE TABLE IF NOT EXISTS MOVIES" + "(" +
                        "id int PRIMARY KEY AUTO_INCREMENT, " +
                        "title varchar(255), " +
                        "genre varchar(255), " +
                        "yearOfRelease INTEGER" + ")";
                System.out.println("Execute sql query: " + tableSql);

                statement.execute(tableSql);

                System.out.println("Table successfully created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static void createMovieTable() {
//        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
//            try (Statement statement = con.createStatement()) {
//                if (tableExists("MOVIES", con)) {
//                    System.out.println("Your table has been found. Deleting your table!");
//                    deleteMovieTable();
//                    String tableSql = "CREATE TABLE IF NOT EXISTS MOVIES" + "(" +
//                            "id int PRIMARY KEY AUTO_INCREMENT, " +
//                            "title varchar(255), " +
//                            "genre varchar(255), " +
//                            "yearOfRelease INTEGER" + ")";
//                    System.out.println("Execute sql query: " + tableSql);
//
//                    statement.execute(tableSql);
//
//                    System.out.println("Table successfully created");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @SneakyThrows
//    public static boolean tableExists(String tableName, Connection conn) {
//        boolean found = false;
//        DatabaseMetaData databaseMetaData = conn.getMetaData();
//        ResultSet rs = databaseMetaData.getTables(null, null, tableName, null);
//        while (rs.next()) {
//            String name = rs.getString("TABLE_NAME");
//            if (tableName.equals(name)) {
//                found = true;
//                break;
//            }
//        }
//        return found;
//    }

    public static void deleteMovieTable() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String tableSql = "DROP TABLE IF EXISTS MOVIES";
                System.out.println("Execute sql query: " + tableSql);

                statement.execute(tableSql);

                System.out.println("Table successfully deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertMoviesIntoTable() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String sql = "INSERT INTO MOVIES(title, genre, yearOfRelease)" +
                        "VALUES('Plutea o ranga  pe Siret', 'Horror', 2022)," +
                        "('Ca toporul la fund', 'Comedy', 2008)," +
                        "('Ce faceam asta vara', 'Tragedy', 2011)";
                System.out.println("Put values in table: " + sql);

                int affectedRows = statement.executeUpdate(sql);

                System.out.println("Command successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertMoviesWithParam(String title, String genre, int yearOfRelease) {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            String sql = "INSERT INTO movies(title, genre, yearOfRelease) VALUES(?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, genre);
                ps.setDouble(3, yearOfRelease);

                int affectedRows = ps.executeUpdate();

                System.out.println("Command successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord(int id, String genre) {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            String sql = "UPDATE MOVIES SET genre = ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, genre);
                ps.setInt(2, id);

                System.out.println("Execute update: " + sql);
                int affectedRows = ps.executeUpdate();

                System.out.println("Update successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecord(int id) {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            String sql = "DELETE FROM movies WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                System.out.println("Execute delete: " + sql);
                int affectedRows = ps.executeUpdate();

                System.out.println("Movie with ID = " + id + " was deleted. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllRecords() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement s = con.createStatement()) {
                String sql = "SELECT * FROM movies";
                try (ResultSet resultSet = s.executeQuery(sql)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        String genre = resultSet.getString("genre");
                        int yearOfRelease = resultSet.getInt("yearOfRelease");

                        System.out.println("Movie -> ID: " + id + ", Title: " + title + ", Genre: " + genre + ", Release Year: " + yearOfRelease);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        task1();
    }
}
