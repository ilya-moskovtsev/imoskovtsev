package ru.job4j.servlets.crud;

import java.util.Objects;

public class JsonPerson {
    private String firstName;
    private String lastName;
    private String gender;
    private String description;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonPerson person = (JsonPerson) o;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(gender, person.gender)
                && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender, description);
    }
}
