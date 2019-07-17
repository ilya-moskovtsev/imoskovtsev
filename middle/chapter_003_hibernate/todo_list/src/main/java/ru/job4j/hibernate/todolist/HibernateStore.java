package ru.job4j.hibernate.todolist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Persistent layer.
 */
public class HibernateStore implements Store {
    private static final Logger LOG = LogManager.getLogger(HibernateStore.class.getName());
    private static final HibernateStore INSTANCE = new HibernateStore();
    private static SessionFactory sessionFactory;

    public HibernateStore() {
        setUp();
    }

    public static HibernateStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Item> getItems() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item", Item.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return items;
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
            LOG.error(e.getMessage(), e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
