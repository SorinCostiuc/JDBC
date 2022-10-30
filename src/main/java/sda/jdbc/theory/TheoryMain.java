package sda.jdbc.theory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TheoryMain {
    /*
      JAVA DATABASE CONNECTIVITY - it's an API that allows us to connect to a server
      - has 4 components:
           JDBC API,
           DRIVER Manager,
           Test Suite
           JDBC-ODBC Bridge
      - 2 methods to connect to databases:
           DriverManager
           DataSource
      - Statement class is an interface that allows us to create requests/condition: "select serial from Netflix where name = X"
      - Prepare Statements uses statement as "select serial from Netflix where name = ?" / setString (1, "x") - allows for dynamic values (reuse of a statement)
      - Callable Statement allows us to call a function or procedure from SQL query
      - methods:
           execute -> execute SQL commands
           executeUpdate -> execute SQL command and return nr. of lines affected
           executeQuery -> returns the result of a select = returns the table result and uses the "next()" method to see the result on each line; next() returns a boolean if next line exists
       */
//    CONNECTIVITY
    public static void connectToDB() {
        //Using DriverManager to get the connection
        /*
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda_database",
                    "sda_user",
                    "sda_pass");
            //throw exception because:
                -database not exist
                - user not exist
                -wrong password
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        */
//        refactoring the above code
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda_database", "sda_user", "sda_pass")) {
            System.out.println("Successfully connected to DB");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //    Execute SQL command
    public static void main(String[] args) {

        System.out.println("Holla");
    }
}