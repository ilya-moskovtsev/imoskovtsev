package ru.job4j.lsp;

public class RecyclableProductDecorator extends ProductDecorator {
    private boolean isRecyclable;

    public RecyclableProductDecorator(Product product) {
        super(product);
        this.isRecyclable = true;
    }

    public boolean isRecyclable() {
        return isRecyclable;
    }

    public void setRecyclable(boolean recyclable) {
        isRecyclable = recyclable;
    }
}
