package ru.job4j.lsp;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public abstract class Product {
    private String name;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private MonetaryAmount price;
    private Double discount;

    public Product(String name, LocalDate creationDate, LocalDate expirationDate, MonetaryAmount price, Double discount) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
