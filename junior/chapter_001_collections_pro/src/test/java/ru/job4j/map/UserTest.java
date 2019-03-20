package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

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
        String name = "name";
        int children = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(1988, Calendar.JANUARY, 31);

        // two users have same fields
        User user1 = new User(name, children, calendar);
        User user2 = new User(name, children, calendar);

        Map<User, Object> map = new HashMap<>();

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

        assertTrue("hashCode is different", user1.hashCode() != user2.hashCode());
        assertNotEquals("users not equal", user1, user2);
    }
}