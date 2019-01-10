package ru.job4j.banktransfer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void addUser() {
        final Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("name1", "100"), new ArrayList<>());
        expected.put(new User("name2", "200"), new ArrayList<>());
        expected.put(new User("name3", "300"), new ArrayList<>());


        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addUser(new User("name2", "200"));
        bank.addUser(new User("name3", "300"));
        final Map<User, List<Account>> result = bank.getMap();

        assertThat(result, is(expected));
    }

    @Test
    public void deleteUser() {
        final Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("name1", "100"), new ArrayList<>());

        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addUser(new User("name2", "200"));
        bank.deleteUser(new User("name2", "200"));
        final Map<User, List<Account>> result = bank.getMap();

        assertThat(result, is(expected));
    }

    @Test
    public void addAccountToUser() {
        final Map<User, List<Account>> expected = new HashMap<>();
        expected.put(
                new User("name1", "100"),
                Collections.singletonList(new Account(10000d, "1001")));

        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addAccountToUser("100", new Account(10000d, "1001"));
        final Map<User, List<Account>> result = bank.getMap();

        assertThat(result, is(expected));
    }

    @Test
    public void deleteAccountFromUser() {
        final Map<User, List<Account>> expected = new HashMap<>();
        expected.put(
                new User("name1", "100"),
                Collections.singletonList(new Account(10000d, "1002")));


        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addAccountToUser("100", new Account(10000d, "1001"));
        bank.addAccountToUser("100", new Account(10000d, "1002"));
        bank.deleteAccountFromUser("100", new Account(0d, "1001"));
        final Map<User, List<Account>> result = bank.getMap();

        assertThat(result, is(expected));
    }

    @Test
    public void getUserAccounts() {
        final List<Account> expected = Arrays.asList(
                new Account(10000d, "1001"),
                new Account(10000d, "1002")
        );

        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addAccountToUser("100", new Account(10000d, "1001"));
        bank.addAccountToUser("100", new Account(10000d, "1002"));
        final List<Account> result = bank.getUserAccounts("100");

        assertThat(result, is(expected));
    }

    @Test
    public void transferMoney() {
        final Bank bank = new Bank();
        bank.addUser(new User("name1", "100"));
        bank.addUser(new User("name2", "200"));
        bank.addAccountToUser("100", new Account(10000d, "1001"));
        bank.addAccountToUser("200", new Account(10000d, "1002"));

        // positive case
        final boolean hastransferred1 = bank.transferMoney(
                "100",
                "1001",
                "200",
                "1002",
                500d);

        assertThat(hastransferred1, is(true));

        final List<Account> accounts1 = bank.getUserAccounts("100");
        final int index = accounts1.indexOf(new Account(0d, "1001"));
        final Account account1 = accounts1.get(index);

        assertThat(account1.getValue(), is(9500d));

        final List<Account> accounts2 = bank.getUserAccounts("200");
        final int index2 = accounts2.indexOf(new Account(0d, "1002"));
        final Account account2 = accounts2.get(index);

        assertThat(account2.getValue(), is(10500d));

        // srcAccount not found
        final boolean hastransferred2 = bank.transferMoney(
                "100",
                "not found",
                "200",
                "1002",
                500d);
        assertThat(hastransferred2, is(false));

        // dstAccount not found
        final boolean hastransferred3 = bank.transferMoney(
                "100",
                "1001",
                "200",
                "not found",
                500d);
        assertThat(hastransferred3, is(false));

        // not enough money in srcAccount
        final boolean hastransferred4 = bank.transferMoney(
                "100",
                "1001",
                "200",
                "1002",
                20000d);
        assertThat(hastransferred4, is(false));
    }
}