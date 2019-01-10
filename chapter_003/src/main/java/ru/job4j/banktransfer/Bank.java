package ru.job4j.banktransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bank {
    private Map<User, List<Account>> map = new HashMap<>();

    public Map<User, List<Account>> getMap() {
        return map;
    }

    public void addUser(User user) {
        map.putIfAbsent(user, new ArrayList<>());

    }

    public void deleteUser(User user) {
        map.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        final List<Account> accounts = getUserAccounts(passport);
        accounts.add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        final List<Account> accounts = getUserAccounts(passport);
        accounts.remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return map.entrySet().stream().filter(entry -> passport.equals(entry.getKey().getPassport())).collect(Collectors.toList()).get(0).getValue();
    }

    public Account getUserAccount(String passport, String requisite) {
        final List<Account> accounts = getUserAccounts(passport);
        final int index = accounts.indexOf(new Account(0d, requisite));
        if (index == -1) {
            // account not found
            return null;
        }
        return accounts.get(index);
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, Double amount) {
        final Account srcAccount = getUserAccount(srcPassport, srcRequisite);
        final Account dstAccount = getUserAccount(dstPassport, dstRequisite);
        if (srcAccount == null || dstAccount == null) {
            // srcAccount or dstAccount not found
            return false;
        }
        if (srcAccount.getValue() < amount) {
            // not enough money in srcAccount
            return false;
        }

        srcAccount.setValue(srcAccount.getValue() - amount);
        dstAccount.setValue(dstAccount.getValue() + amount);

        return true;
    }
}
