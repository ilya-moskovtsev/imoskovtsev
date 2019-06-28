package ru.job4j.isp;

import com.google.common.base.Joiner;

import java.util.List;
import java.util.function.Consumer;

public class MenuItem implements Print, Select {
    private String name;
    private String prefix;
    private List<MenuItem> items;

    public MenuItem(String name, String prefix, List<MenuItem> items) {
        this.name = name;
        this.prefix = prefix;
        this.items = items;
    }

    @Override
    public void print(String prefix, Consumer<String> output) {
        output.accept(Joiner.on(" ").join(prefix, name));
        if (items != null) {
            items.forEach(menuItem -> menuItem.print(Joiner.on("").join(prefix, prefix), output));
        }
    }

    @Override
    public void onSelect() {

    }
}
