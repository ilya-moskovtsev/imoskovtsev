package ru.job4j.lsp;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class WarehouseStrategy implements StoreStrategy {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void doOperation(List<Product> products) {
        ListIterator<Product> iterator = products.listIterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            long total = DAYS.between(product.getCreationDate(), product.getExpirationDate());
            long passed = DAYS.between(product.getCreationDate(), LocalDate.now());
            long percentage = passed * 100 / total;
            if (percentage < 25) {
                this.products.add(product);
                iterator.remove();
            }
        }
    }
}
