package sda.jdbc.practical;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        deleteMovieTable();
        createMovieTable();
        insertMoviesIntoTable();

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

    public static void deleteMovieTable() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String tableSql = "DROP TABLE IF EXISTS MOVIES";
                System.out.println("Execute sql query: " + tableSql);

                statement.execute(tableSql);

                System.out.println("Table successfully created");
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

    public static void main(String[] args) {
        task1();
    }
}
