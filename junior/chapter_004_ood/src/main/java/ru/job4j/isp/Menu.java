package ru.job4j.isp;

import java.util.List;
import java.util.function.Consumer;

public class Menu implements Print {
    private List<MenuItem> items;

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public void print(String prefix, Consumer<String> output) {
        prefix = (prefix == null) ? "" : prefix;
        for (MenuItem menuItem : items) {
            menuItem.print(prefix, output);
        }
    }
}
