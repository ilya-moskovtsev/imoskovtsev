package ru.job4j.lsp;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public class Meat extends Product {
    public Meat(String name, LocalDate creationDate, LocalDate expirationDate, MonetaryAmount price, Double discount) {
        super(name, creationDate, expirationDate, price, discount);
    }
}
