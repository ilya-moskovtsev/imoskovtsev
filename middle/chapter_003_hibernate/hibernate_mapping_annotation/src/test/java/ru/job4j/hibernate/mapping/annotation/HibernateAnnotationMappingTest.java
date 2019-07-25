package ru.job4j.hibernate.mapping.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class HibernateAnnotationMappingTest {
    private SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
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

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void addUser() {
        performAddUser();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User resultUser = session.createQuery("from User", User.class).getSingleResult();
        int resultAddsSize = resultUser.getAdds().size();
        int resultCommentsSize = resultUser.getComments().size();

        session.getTransaction().commit();
        session.close();


        assertThat(resultUser.getName(), is("user name"));
        assertThat(resultUser.getLogin(), is("user login"));
        assertThat(resultUser.getEmail(), is("user email"));
        assertThat(resultUser.getPassword(), is("user password"));
        assertThat(resultUser.getDateCreated(), is(LocalDate.now().minusMonths(1)));
        assertThat(resultAddsSize, is(0));
        assertThat(resultCommentsSize, is(0));
    }

    @Test
    public void updateUser() {
        performAddUser();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User updateMe = session.createQuery("from User", User.class).getSingleResult();
        updateMe.setName("new user name");
        updateMe.setLogin("new user login");
        updateMe.setEmail("new user email");
        updateMe.setPassword("new user password");
        updateMe.setDateCreated(LocalDate.now().minusMonths(2));

        Advertisement add = new Advertisement();
        session.save(add);
        updateMe.setAdds(List.of(add));

        Comment comment = new Comment();
        session.save(comment);
        updateMe.setComments(List.of(comment));

        session.update(updateMe);

        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();

        User resultUser = session.createQuery("from User", User.class).getSingleResult();
        int resultAddsSize = resultUser.getAdds().size();
        int resultCommentsSize = resultUser.getComments().size();

        session.getTransaction().commit();
        session.close();


        assertThat(resultUser.getName(), is("new user name"));
        assertThat(resultUser.getLogin(), is("new user login"));
        assertThat(resultUser.getEmail(), is("new user email"));
        assertThat(resultUser.getPassword(), is("new user password"));
        assertThat(resultUser.getDateCreated(), is(LocalDate.now().minusMonths(2)));
        assertThat(resultAddsSize, is(1));
        assertThat(resultCommentsSize, is(1));
    }

    @Test
    public void deleteUser() {
        performAddUser();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User deleteMe = session.createQuery("from User", User.class).getSingleResult();

        session.delete(deleteMe);

        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> resultList = session.createQuery("from User", User.class).getResultList();

        session.getTransaction().commit();
        session.close();

        assertThat(resultList.size(), is(0));
    }

    @Test
    public void testAnnotationMapping() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User seller = new User();
        seller.setName("seller name");
        seller.setLogin("seller login");
        seller.setEmail("seller email");
        seller.setPassword("seller password");
        seller.setDateCreated(LocalDate.now().minusMonths(1));
        seller.setComments(Collections.emptyList());

        User buyer = new User();
        buyer.setName("buyer name");
        buyer.setLogin("buyer login");
        buyer.setEmail("buyer email");
        buyer.setPassword("buyer password");
        buyer.setDateCreated(LocalDate.now().minusMonths(2));
        buyer.setAdds(Collections.emptyList());

        Advertisement add = new Advertisement();
        add.setMake("make1");
        add.setModel("model1");
        add.setPrice(BigDecimal.valueOf(1000));
        add.setNew(false);
        add.setYearManufactured(LocalDate.now().minusYears(20));
        add.setTransmission("transmission1");
        add.setBody("body1");
        add.setEngine("engine1");
        add.setMileage(200000);
        add.setEngineCapacity(5d);
        add.setDrive("drive1");
        add.setSteeringWheel("left");
        add.setBroken(true);
        add.setBrakeHorsepower(500);
        add.setNumberOfOwners(5);
        add.setColor("red");
        add.setPhoto("path to photo1");
        add.setSold(true);
        add.setAuthor(seller);
        add.setText("add text");
        add.setPosted(LocalDateTime.now().minusDays(7));

        Comment comment = new Comment();
        comment.setText("comment text");
        comment.setPosted(LocalDateTime.now().minusDays(6));
        comment.setAdd(add);
        comment.setAuthor(buyer);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        add.setComments(comments);
        buyer.setComments(comments);

        List<Advertisement> adds = new ArrayList<>();
        adds.add(add);
        seller.setAdds(adds);

        session.save(seller);
        session.save(buyer);
        session.save(comment);
        session.save(add);

        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> fromUser = session.createQuery("from User", User.class).list();
        List<Advertisement> fromAdvertisement = session.createQuery("from Advertisement", Advertisement.class).list();
        List<Comment> fromComment = session.createQuery("from Comment", Comment.class).list();

        session.getTransaction().commit();
        session.close();


        String sellerName = fromUser.get(0).getName();
        String buyerName = fromUser.get(1).getName();

        assertThat(sellerName, is("seller name"));
        assertThat(buyerName, is("buyer name"));

        assertThat(fromAdvertisement.get(0).getMake(), is("make1"));
        assertThat(fromAdvertisement.get(0).getAuthor().getName(), is(sellerName));

        assertThat(fromComment.get(0).getText(), is("comment text"));
        assertThat(fromComment.get(0).getAuthor().getName(), is(buyerName));
    }

    private void performAddUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User inputUser = new User();
        inputUser.setName("user name");
        inputUser.setLogin("user login");
        inputUser.setEmail("user email");
        inputUser.setPassword("user password");
        inputUser.setDateCreated(LocalDate.now().minusMonths(1));
        inputUser.setAdds(Collections.emptyList());
        inputUser.setComments(Collections.emptyList());

        session.save(inputUser);

        session.getTransaction().commit();
        session.close();
    }
}