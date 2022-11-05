package sda.hibernate.theory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sda.hibernate.theory.entity.Employee;


public class HibernateMain {
    public static void insertEmployee() {
        System.out.println("Persist employee ...");
        try (SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            try (Session session = sf.openSession()) {
                Transaction transaction = session.beginTransaction();
                Employee employee1 = new Employee();
                employee1.setName("Sorin");
                employee1.setPosition("Manager");
                employee1.setSalary(4000);

                session.persist(employee1);

                transaction.commit();
            }
        }
    }


    public static void main(String[] args) {
        insertEmployee();
    }
}
