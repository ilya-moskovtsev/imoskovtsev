package ru.job4j.servlets.crud;

import com.google.common.base.Joiner;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private LocalDate dateCreated;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return Joiner.on("").join(
                "User{id=", id,
                ", name='", name, '\'',
                ", login='", login, '\'',
                ", email='", email, '\'',
                ", dateCreated=", dateCreated, '}'
        );
    }
}
