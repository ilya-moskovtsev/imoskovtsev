package ru.job4j.lsp;

import java.util.List;

public interface StoreStrategy {
    void doOperation(List<Product> products);
}
