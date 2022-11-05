package sda.hibernate.theory;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import sda.hibernate.theory.entity.Employee;

import java.util.List;


public class HibernateMain {
    static SessionFactory sf = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void insertEmployee(Employee... employees) {
        System.out.println("Persist employee ...");
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Employee employee : employees) {
                session.persist(employee);
            }
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
            // Assemble query
            JpaCriteriaQuery<Employee> jpaCriteriaQuery = session
                    .getCriteriaBuilder()
                    .createQuery(Employee.class); // resulted object will be of type Employee
            jpaCriteriaQuery.from(Employee.class); // FROM EMPLOYEE

            //Create query
            TypedQuery<Employee> typedQuery = session.createQuery(jpaCriteriaQuery);
            //Execute query -> get ResultSet(JDBC analogy) -> transform resultSet into List<Employee>
            return typedQuery.getResultList();
        }
    }

    public static void updateEmployee(Employee e) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(e);

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
//        insertEmployee(employee1, employee2);
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

        Employee employee3 = new Employee();
        employee3.setName("Alexandra");
        employee3.setPosition("Java Dev");
        employee3.setSalary(4200);
        employee3.setId(6L);
        updateEmployee(employee3);
        System.out.println("----------");

    }
}
