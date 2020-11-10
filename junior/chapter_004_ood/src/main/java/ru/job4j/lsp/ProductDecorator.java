package ru.job4j.lsp;

public abstract class ProductDecorator extends Product {

    public ProductDecorator(Product product) {
        super(product.getName(), product.getCreationDate(), product.getExpirationDate(), product.getPrice(), product.getDiscount());
    }
}
