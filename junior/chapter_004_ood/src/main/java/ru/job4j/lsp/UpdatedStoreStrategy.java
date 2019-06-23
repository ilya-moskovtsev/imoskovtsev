package ru.job4j.lsp;

import java.util.List;

public interface UpdatedStoreStrategy extends StoreStrategy {
    void updatedDoOperation(List<ProductDecorator> products);
}
