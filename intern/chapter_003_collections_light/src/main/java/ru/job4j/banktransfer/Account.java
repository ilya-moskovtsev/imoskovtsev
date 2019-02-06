package ru.job4j.banktransfer;

import java.util.Objects;

public class Account {
    private Double value;
    private String requisites;

    public Account(Double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
