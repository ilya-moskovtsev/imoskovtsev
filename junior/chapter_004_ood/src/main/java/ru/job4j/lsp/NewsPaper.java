package ru.job4j.lsp;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public class NewsPaper extends Product {
    private boolean isRecyclable;

    public NewsPaper(String name, LocalDate creationDate, LocalDate expirationDate, MonetaryAmount price, Double discount, boolean isRecyclable) {
        super(name, creationDate, expirationDate, price, discount);
        this.isRecyclable = isRecyclable;
    }

    public boolean isRecyclable() {
        return isRecyclable;
    }

    public void setRecyclable(boolean recyclable) {
        isRecyclable = recyclable;
    }
}
