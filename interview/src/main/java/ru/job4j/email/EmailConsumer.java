package ru.job4j.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmailConsumer implements Runnable {
    private static final Logger LOG = LogManager.getLogger(EmailConsumer.class.getName());
    private SessionFactory sessionFactory;

    public EmailConsumer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Email> resultList = session.createQuery("from Email e where e.sent = :sent", Email.class)
                .setParameter("sent", false)
                .getResultList();
        if (resultList.size() == 0) {
            LOG.info("there is nothing to send");
        } else {
            send(resultList);
        }
        session.getTransaction().commit();
        session.close();
    }

    private void send(List<Email> emails) {
        emails.forEach(email -> {
            LOG.info(String.format("sending email %s", email.getText()));
            email.setSent(true);
        });
    }
}
