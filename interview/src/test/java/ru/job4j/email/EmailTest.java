package ru.job4j.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailTest {
    private static final Logger LOG = LogManager.getLogger(EmailTest.class.getName());
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testBasicUsage() {
        EmailProducer producer = new EmailProducer(sessionFactory);
        EmailConsumer consumer = new EmailConsumer(sessionFactory);

        ScheduledExecutorService executor = Executors
                .newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(producer, 100, 100, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consumer, 1000, 1000, TimeUnit.MILLISECONDS);


        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        showAll();
    }

    private void showAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Email> emailList = session.createQuery("from Email", Email.class).list();
        for (Email email : emailList) {
            LOG.info(email);
        }
        session.getTransaction().commit();
        session.close();
    }
}