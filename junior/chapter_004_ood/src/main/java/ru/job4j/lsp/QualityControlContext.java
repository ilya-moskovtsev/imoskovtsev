package ru.job4j.lsp;

import java.util.List;

public class QualityControlContext {
    private StoreStrategy storeStrategy;

    public QualityControlContext(StoreStrategy storeStrategy) {
        this.storeStrategy = storeStrategy;
    }

    public void executeStrategy(List<Product> products) {
        storeStrategy.doOperation(products);
    }
}
