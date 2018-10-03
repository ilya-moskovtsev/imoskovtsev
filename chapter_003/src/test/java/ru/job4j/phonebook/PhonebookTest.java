package ru.job4j.phonebook;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class PhonebookTest {
    @Test
    public void whenNameContainsQueryShouldFindPerson() {
        Phonebook phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> result = phonebook.search("Pet");
        assertThat(result.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenSurnameContainsQueryShouldFindPerson() {
        Phonebook phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> result = phonebook.search("sent");
        assertThat(result.iterator().next().getName(), is("Petr"));
    }

    @Test
    public void whenPhoneContainsQueryShouldFindPerson() {
        Phonebook phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> result = phonebook.search("72");
        assertThat(result.iterator().next().getAddress(), is("Bryansk"));
    }

    @Test
    public void whenAddressContainsQueryShouldFindPerson() {
        Phonebook phones = new Phonebook();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> result = phones.search("ans");
        assertThat(result.iterator().next().getPhone(), is("534872"));
    }

    @Test
    public void whenQueryMatchesMultiplePersonsShouldFindAllThatMatch() {
        Phonebook phones = new Phonebook();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("no match", "no match", "no match", "no match")
        );

        List<Person> result = phones.search("348");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.get(1).getName(), is("Petr"));
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenQueryMatchesMultiplePropertiesOfOnePersonShouldBeNoDuplicates() {
        Phonebook phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Petr", "Petr", "Petr")
        );

        List<Person> result = phonebook.search("et");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.size(), is(1));
    }

    @Test
    public void searchShouldBeCaseInsensitive() {
        Phonebook phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );

        List<Person> result = phonebook.search("pEtR");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.size(), is(1));
    }
}
