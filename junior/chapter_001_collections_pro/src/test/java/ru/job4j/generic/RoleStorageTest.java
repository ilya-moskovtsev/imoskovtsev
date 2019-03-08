package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStorageTest {

    private RoleStorage storage;

    @Before
    public void setUp() {
        storage = new RoleStorage(4);
        storage.add(new Role("role1"));
        storage.add(new Role("role2"));
        storage.add(new Role("role3"));
    }

    @Test
    public void add() {
        final String ROLE_ID = "role4";
        Role role = new Role(ROLE_ID);
        storage.add(role);
        Role result = storage.findById(ROLE_ID);

        assertThat(result.getId(), is(role.getId()));
    }

    @Test
    public void replace() {
        final String ROLE_ID = "role4";
        Role role = new Role(ROLE_ID);
        boolean replaceResult1 = storage.replace("role3", role);
        Role findByIdResult = storage.findById(ROLE_ID);
        boolean replaceResult2 = storage.replace("role3", role);

        assertThat(replaceResult1, is(true));
        assertThat(findByIdResult.getId(), is(role.getId()));
        assertThat(replaceResult2, is(false));
    }

    @Test
    public void delete() {
        final String ROLE_ID = "role2";
        Role role = new Role(ROLE_ID);
        Role findByIdResult = storage.findById(ROLE_ID);
        boolean deleteResult1 = storage.delete(ROLE_ID);
        boolean deleteResult2 = storage.delete(ROLE_ID);

        assertThat(findByIdResult.getId(), is(role.getId()));
        assertThat(deleteResult1, is(true));
        assertThat(deleteResult2, is(false));
    }
}