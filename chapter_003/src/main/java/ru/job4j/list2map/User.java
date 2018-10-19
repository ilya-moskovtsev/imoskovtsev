package ru.job4j.list2map;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String city;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.getId()
                && Objects.equals(name, user.getName())
                && Objects.equals(city, user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }
}
