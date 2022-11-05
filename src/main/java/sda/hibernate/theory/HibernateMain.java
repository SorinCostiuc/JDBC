package sda.hibernate.theory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sda.hibernate.theory.entity.Employee;


public class HibernateMain {
    public static void insertEmployee(Employee... employees) {
        System.out.println("Persist employee ...");
        try (SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            try (Session session = sf.openSession()) {
                Transaction transaction = session.beginTransaction();
                for (Employee employee : employees) {
                    session.persist(employee);
                }
                transaction.commit();
            }
        }
    }


    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setName("Sorin");
        employee1.setPosition("Manager");
        employee1.setSalary(4000);

        Employee employee2 = new Employee();
        employee2.setName("Bogdan");
        employee2.setPosition("Scrum");
        employee2.setSalary(3000);

        insertEmployee(employee1, employee2);
        System.out.println("Heck yeah");
    }
}
