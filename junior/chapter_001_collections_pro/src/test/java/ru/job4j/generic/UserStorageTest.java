package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    private UserStorage storage;

    @Before
    public void setUp() {
        storage = new UserStorage(4);
        storage.add(new User("user1"));
        storage.add(new User("user2"));
        storage.add(new User("user3"));
    }

    @Test
    public void add() {
        final String USER_ID = "user4";
        User user = new User(USER_ID);
        storage.add(user);
        User result = storage.findById(USER_ID);

        assertThat(result.getId(), is(user.getId()));
    }

    @Test
    public void replace() {
        final String USER_ID = "user4";
        User user = new User(USER_ID);
        boolean replaceResult1 = storage.replace("user3", user);
        User findByIdResult = storage.findById(USER_ID);
        boolean replaceResult2 = storage.replace("user3", user);

        assertThat(replaceResult1, is(true));
        assertThat(findByIdResult.getId(), is(user.getId()));
        assertThat(replaceResult2, is(false));
    }

    @Test
    public void delete() {
        final String USER_ID = "user2";
        User user = new User(USER_ID);
        User findByIdResult = storage.findById(USER_ID);
        boolean deleteResult1 = storage.delete(USER_ID);
        boolean deleteResult2 = storage.delete(USER_ID);

        assertThat(findByIdResult.getId(), is(user.getId()));
        assertThat(deleteResult1, is(true));
        assertThat(deleteResult2, is(false));
    }
}