package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.time.temporal.ChronoUnit.DAYS;

public class UpdatedTrashStrategy extends TrashStrategy implements UpdatedStoreStrategy {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void updatedDoOperation(List<ProductDecorator> products) {
        ListIterator<ProductDecorator> iterator = products.listIterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            long total = DAYS.between(product.getCreationDate(), product.getExpirationDate());
            long passed = DAYS.between(product.getCreationDate(), LocalDate.now());
            long percentage = passed * 100 / total;
            if (percentage >= 100) {
                this.products.add(product);
                iterator.remove();
            }
        }
    }
}
