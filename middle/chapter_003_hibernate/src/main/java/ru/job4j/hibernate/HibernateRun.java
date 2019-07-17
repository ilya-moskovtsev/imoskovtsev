package ru.job4j.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

public class HibernateRun {
    private static final Logger LOG = LogManager.getLogger(HibernateRun.class.getName());
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setUp();

        createUser();
        findUserById();
        updateUserByName();
        findUserByName();
        deleteUserById();
        listUsers();

        tearDown();
    }

    private static void listUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        for (User user : users) {
            LOG.info(user);
        }
        session.getTransaction().commit();
        session.close();
    }

    private static void deleteUserById() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User deleteMe = new User();
        deleteMe.setId(1);
        session.delete(deleteMe);
        session.getTransaction().commit();
        session.close();
    }

    private static void findUserByName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User foundByName = session.createQuery("from User u where u.name = :name", User.class)
                .setParameter("name", "Name 1")
                .getSingleResult();
        LOG.info(foundByName);
        session.getTransaction().commit();
        session.close();
    }

    private static void updateUserByName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("update User u set u.expired = :newExpired where u.name = :name")
                .setParameter("newExpired", LocalDate.now().plusDays(30))
                .setParameter("name", "Name 1")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private static void findUserById() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User foundById = session.get(User.class, 1);
        LOG.info(foundById);
        session.getTransaction().commit();
        session.close();
    }

    private static void createUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User("Name 1", LocalDate.now().plusDays(15)));
        session.getTransaction().commit();
        session.close();
    }


    private static void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
