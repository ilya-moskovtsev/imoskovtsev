package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

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
        var first = new User(name, children, calendar);
        var second = new User(name, children, calendar);

        Map<User, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertThat("hashCode is different", first.hashCode(), not(second.hashCode()));
        assertThat("users not equal", first, not(second));
        assertThat("Map has both users", map.size(), is(2));
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
        var first = new User2(name, children, calendar);
        var second = new User2(name, children, calendar);

        Map<User2, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertThat("hashCode is the same", first.hashCode(), equalTo(second.hashCode()));
        assertThat("users not equal", first, not(second));
        assertThat("Map has both users", map.size(), is(2));
    }

    /**
     * Equals is overridden.
     */
    public static class User3 {
        public String name;
        public int children;
        public Calendar birthday;

        public User3(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User3 user3 = (User3) o;
            return children == user3.children
                    && Objects.equals(name, user3.name)
                    && Objects.equals(birthday, user3.birthday);
        }

        /**
         * --- maven-checkstyle-plugin:2.17:check (validate) @ chapter_001_collections_pro ---
         * error: Definition of 'equals()' without corresponding definition of 'hashCode()'
         *
         * @return result of default hashCode method
         */
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    @Test
    public void equalsIsOverridden() {
        // two users have same fields
        var first = new User3(name, children, calendar);
        var second = new User3(name, children, calendar);

        Map<User3, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertThat("hashCode is different", first.hashCode(), not(second.hashCode()));
        assertThat("users are equal", first, equalTo(second));
        assertThat("Map has both users", map.size(), is(2));
    }

    /**
     * Equals and hashCode are overridden.
     */
    public static class User4 {
        public String name;
        public int children;
        public Calendar birthday;

        public User4(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User4 user4 = (User4) o;
            return children == user4.children
                    && Objects.equals(name, user4.name)
                    && Objects.equals(birthday, user4.birthday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }
    }

    @Test
    public void equalsAndHashCodeAreOverridden() {
        // two users have same fields
        var first = new User4(name, children, calendar);
        var second = new User4(name, children, calendar);

        Map<User4, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);

        assertThat("hashCode is the same", first.hashCode(), equalTo(second.hashCode()));
        assertThat("users not equal", first, equalTo(second));
        assertThat("Map has one user", map.size(), is(1));
    }
}