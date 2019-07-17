package ru.job4j.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private int id;
    private String name;
    private LocalDate expired;

    public User() {
        // this form used by Hibernate
    }

    public User(String name, LocalDate expired) {
        // for application use, to create new users
        this.name = name;
        this.expired = expired;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpired() {
        return expired;
    }

    public void setExpired(LocalDate expired) {
        this.expired = expired;
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
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(expired, user.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expired);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", expired=" + expired
                + '}';
    }
}
