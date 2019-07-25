package ru.job4j.hibernate.todolist;

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
    private static final HibernateStore INSTANCE = new HibernateStore();
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

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

    @Override
    public void done(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("update Item u set u.done = :newDone where u.id = :id")
                .setParameter("newDone", item.isDone())
                .setParameter("id", item.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
