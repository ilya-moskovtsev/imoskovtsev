package ru.job4j.lsp;

import java.util.List;

public class UpdatedQualityControlContext {
    private UpdatedStoreStrategy updatedStoreStrategy;
    private QualityControlContext oldQualityControlContext;

    public UpdatedQualityControlContext(UpdatedStoreStrategy updatedStoreStrategy) {
        oldQualityControlContext = new QualityControlContext(updatedStoreStrategy);
        this.updatedStoreStrategy = updatedStoreStrategy;
    }

    public void executeStrategy(List<Product> products) {
        oldQualityControlContext.executeStrategy(products);
    }

    public void executeUpdatedStrategy(List<ProductDecorator> products) {
        updatedStoreStrategy.updatedDoOperation(products);
    }
}
