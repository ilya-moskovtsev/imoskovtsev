package ru.job4j.syncuserstorage;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.syncuserstorage.SyncUserStorage.User;

public class SyncUserStorageTest {

    @Test
    public void whenAddNewUserThenTrue() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var hasAdded1 = storage.add(user1);
        assertThat(hasAdded1, is(true));
    }

    @Test
    public void whenAddAlreadyExistingUserThenFalse() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var user2 = new User(1, 100);
        storage.add(user1);
        var hasAdded2 = storage.add(user2);
        assertThat(hasAdded2, is(false));
    }

    @Test
    public void whenUpdateExistingUserThenTrueAndAmountUpdated() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        storage.add(user1);

        var hasUpdated = storage.update(new User(1, 200));

        assertThat(hasUpdated, is(true));
        assertThat(storage.getUsers().get(0).getAmount(), is(200));
    }

    @Test
    public void whenUpdateNonExistingUserThenFalseAndAmountNotUpdated() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        storage.add(user1);

        var hasUpdated = storage.update(new User(2, 200));

        assertThat(hasUpdated, is(false));
        assertThat(storage.getUsers().get(0).getAmount(), is(100));
    }

    @Test
    void whenDeleteExistingUserThenTrue() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        storage.add(user1);

        var hasDeleted = storage.delete(new User(1, 100));

        assertThat(hasDeleted, is(true));
        assertThat(storage.getUsers().size(), is(0));
    }

    @Test
    void whenDeleteNonExistingUserThenFalse() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        storage.add(user1);

        var hasDeleted = storage.delete(new User(2, 100));
        assertThat(hasDeleted, is(false));
        assertThat(storage.getUsers().size(), is(1));
    }

    @Test
    public void whenTransferFromExistingToExistingThenTrueAndAmountTransferred() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        var hasTransferred = storage.transfer(1, 2, 100);

        assertThat(hasTransferred, is(true));
        assertThat(user1.getAmount(), is(0));
        assertThat(user2.getAmount(), is(300));
    }

    @Test
    public void whenTransferFromNonExistingToExistingThenFalseAndAmountNotChanged() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        var hasTransferred = storage.transfer(3, 2, 100);

        assertThat(hasTransferred, is(false));
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }

    @Test
    public void whenTransferFromExistingToNonExistingThenFalseAndAmountNotChanged() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        var hasTransferred = storage.transfer(1, 3, 100);

        assertThat(hasTransferred, is(false));
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }

    @Test
    public void whenTransferFromNonExistingToNonExistingThenFalseAndAmountNotChanged() {
        var storage = new SyncUserStorage();
        var user1 = new User(1, 100);
        var user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        var hasTransferred = storage.transfer(3, 4, 100);

        assertThat(hasTransferred, is(false));
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }
}
