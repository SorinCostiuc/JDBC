package sda.jdbc.theory;

import java.sql.*;

public class TheoryMain {
    private static final String urlDB = "jdbc:mysql://localhost:3306/sda_database";
    private static final String userNameDB = "root";
    private static final String userPasswordDB = "1990_Bracaraugustanorum";

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
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            System.out.println("Successfully connected to DB");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //    Execute SQL command - Create Table
    public static void executeStatement() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String tableSql = "CREATE TABLE IF NOT EXISTS employees" + "(" +
                        "emp_id int PRIMARY KEY AUTO_INCREMENT, " +
                        "name varchar(30), " +
                        "position varchar(30), " +
                        "salary double" + ")";
                System.out.println("Execute sql query: " + tableSql);

                statement.execute(tableSql);

                System.out.println("Table successfully created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    no parameter to insert and put value in maethod
    public static void insertEmployee() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement statement = con.createStatement()) {
                String sql = "INSERT INTO employees(name, position, salary)" +
                        "VALUES('Sorin', 'Manager', 3000), ('Bogdan', 'CEO', 2000)";
                System.out.println("Put values in table: " + sql);

                int affectedRows = statement.executeUpdate(sql);

                System.out.println("Command successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    parameter to insert
    public static void insertEmpWithParam(String name, String position, double salary) {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            String sql = "INSERT INTO employees(name, position, salary) VALUES(?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, position);
                ps.setDouble(3, salary);

                int affectedRows = ps.executeUpdate();

                System.out.println("Command successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAllFromTable() {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            try (Statement s = con.createStatement()) {
                String sql = "SELECT * FROM employees";
                try (ResultSet resultSet = s.executeQuery(sql)) {
                    while (resultSet.next()) {
                        int empId = resultSet.getInt("emp_id");
                        String name = resultSet.getString("name");
                        String position = resultSet.getString("position");
                        double salary = resultSet.getDouble("salary");

                        System.out.println("Emp -> emp_id: " + empId + ", name:" + name + ", position:" + position + ", salary: " + salary);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSalaryForEmp(int empId, double salary) {
        try (Connection con = DriverManager.getConnection(urlDB, userNameDB, userPasswordDB)) {
            String sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDouble(1, salary);
                ps.setInt(2, empId);

                System.out.println("Execute update: " + sql);
                int affectedRows = ps.executeUpdate();

                System.out.println("Update successfully executed. Affected rows: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
//        connectToDB();
//        executeStatement();
//        insertEmployee();
//        insertEmpWithParam("Dany", "trainer", 1000);
//        insertEmpWithParam("Marius", "army boy", 300);
        System.out.println("Before");
        selectAllFromTable();
        updateSalaryForEmp(1, 8000);
        System.out.println("After");
        selectAllFromTable();
    }
}