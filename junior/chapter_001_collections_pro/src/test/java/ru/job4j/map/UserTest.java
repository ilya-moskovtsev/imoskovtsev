package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private String name = "name";
    private int children = 1;
    private Calendar calendar = Calendar.getInstance();

    @Before
    public void setUp() {
        calendar.set(1988, Calendar.JANUARY, 31);
    }

    /**
     * Equals and hashCode are not overridden.
     */
    public static class User {
        public String name;
        public int children;
        public Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }
    }

    @Test
    public void equalsAndHashCodeAreNotOverridden() {
        // two users have same fields
        User first = new User(name, children, calendar);
        User second = new User(name, children, calendar);

        Map<User, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertTrue("hashCode is different", first.hashCode() != second.hashCode());
        assertNotEquals("users not equal", first, second);
    }

    /**
     * HashCode is overridden.
     */
    public static class User2 {
        public String name;
        public int children;
        public Calendar birthday;

        public User2(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }
    }

    @Test
    public void hashCodeIsOverridden() {
        // two users have same fields
        User2 first = new User2(name, children, calendar);
        User2 second = new User2(name, children, calendar);

        Map<User2, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertEquals("hashCode is the same", first.hashCode(), second.hashCode());
        assertNotEquals("users not equal", first, second);
    }
}