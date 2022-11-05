package sda.hibernate.theory;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import sda.hibernate.theory.embedded.Address;
import sda.hibernate.theory.embedded.Person;
import sda.hibernate.theory.entity.Employee;

import java.util.Arrays;
import java.util.List;


public class HibernateMain {
    static SessionFactory sf = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void insertEmployee(Employee... employees) {
        System.out.println("Persist employee ...");
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();

            Arrays.stream(employees).forEach((session::persist));

            transaction.commit();

        }
    }

    public static Employee getEmployeeById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public static List<Employee> getEmployees() {
        try (Session session = sf.openSession()) {
//             SELECT * FROM EMPLOYEE ---->QUERY creation for the object
            // Assemble query & Create java query
            JpaCriteriaQuery<Employee> jpaCriteriaQuery = session
                    .getCriteriaBuilder()
                    .createQuery(Employee.class); // resulted object will be of type Employee
            jpaCriteriaQuery.from(Employee.class); // FROM EMPLOYEE

            // Execute query
            TypedQuery<Employee> typedQuery = session.createQuery(jpaCriteriaQuery);
            // get ResultSet(JDBC analogy) -> transform resultSet into List<Employee>
            return typedQuery.getResultList();
        }
    }

    public static void updateEmployee(Employee e) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            // update or insert
            session.merge(e);

            session.getTransaction().commit();
        }
    }

    public static void deleteEmployeeById(int id) {
        // Employee e = search employee with id 1
        // delete e
        // we could use the above method @getEmployeeById
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Employee e = getEmployeeById(id);
            if (e == null) {
                System.out.println("Employee with ID: " + id + " not found");
            } else {
                session.remove(e);
                System.out.println("Employee with ID: " + id + " removed");
            }
            session.getTransaction().commit();
        }
    }

    public static void insertPerson() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Address address = new Address();
            address.setCity("Iasi");
            address.setNumber(1);
            address.setCountry("Romanica");

            Person p = new Person();
            p.setName("Gigel");
            p.setAddress(address);

            session.persist(p);

            session.getTransaction().commit();
        }
    }


    public static void main(String[] args) {
//        Employee employee1 = new Employee();
//        employee1.setName("Sorin");
//        employee1.setPosition("Manager");
//        employee1.setSalary(5000);
//
//        Employee employee2 = new Employee();
//        employee2.setName("Bogdan");
//        employee2.setPosition("Scrum");
//        employee2.setSalary(3000);
//
//
//        Employee employee3 = new Employee();
//        employee3.setName("Ana");
//        employee3.setPosition("Python dev");
//        employee3.setSalary(1000);
//        insertEmployee(employee1, employee2, employee3);
//        System.out.println("----------");
//
//        System.out.println(getEmployeeById(1));
//        System.out.println("Heck yeah");
//
//        System.out.println("----------");
//
//        List<Employee> employees = getEmployees();
//        for (Employee e : employees) {
//            System.out.println(e);
//        }
//        System.out.println("----------");

//        Employee employee3 = new Employee();
//        employee3.setName("Alexandra");
//        employee3.setPosition("Java Dev");
//        employee3.setSalary(4200);
//        employee3.setId(6L);
//        updateEmployee(employee3);
//        System.out.println("----------");

//        System.out.println(getEmployeeById(153));
//        deleteEmployeeById(253);
//        System.out.println("----------");
        insertPerson();


    }
}
