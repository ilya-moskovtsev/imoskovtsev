package ru.job4j.phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Phonebook {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Returns a list of all persons that match a query in any property.
     *
     * @param query search query
     * @return persons that match a query
     */
    public List<Person> search(String query) {
        return persons.stream().filter(person ->
                person.toString().toLowerCase().contains(query.toLowerCase())
        ).collect(Collectors.toList());
    }
}
