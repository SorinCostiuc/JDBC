package org.example;

public class Main {
    public static void main(String[] args) {
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
       -

        */


    }
}