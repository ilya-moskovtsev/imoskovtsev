package ru.job4j.email;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class EmailProducer implements Runnable {
    private static final Logger LOG = LogManager.getLogger(EmailProducer.class.getName());
    private SessionFactory sessionFactory;

    public EmailProducer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        delay();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LOG.info("creating email");
        session.save(new Email(RandomStringUtils.randomAlphabetic(10), false));
        session.getTransaction().commit();
        session.close();
    }

    private void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
