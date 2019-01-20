package ru.job4j.phonebook;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhonebookTest {
    @Test
    public void whenNameContainsQueryShouldFindPerson() {
        var phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var result = phonebook.search("Pet");
        assertThat(result.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenSurnameContainsQueryShouldFindPerson() {
        var phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var result = phonebook.search("sent");
        assertThat(result.iterator().next().getName(), is("Petr"));
    }

    @Test
    public void whenPhoneContainsQueryShouldFindPerson() {
        var phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var result = phonebook.search("72");
        assertThat(result.iterator().next().getAddress(), is("Bryansk"));
    }

    @Test
    public void whenAddressContainsQueryShouldFindPerson() {
        var phones = new Phonebook();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var result = phones.search("ans");
        assertThat(result.iterator().next().getPhone(), is("534872"));
    }

    @Test
    public void whenQueryMatchesMultiplePersonsShouldFindAllThatMatch() {
        var phones = new Phonebook();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("no match", "no match", "no match", "no match")
        );

        var result = phones.search("348");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.get(1).getName(), is("Petr"));
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenQueryMatchesMultiplePropertiesOfOnePersonShouldBeNoDuplicates() {
        var phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Petr", "Petr", "Petr")
        );

        var result = phonebook.search("et");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.size(), is(1));
    }

    @Test
    public void searchShouldBeCaseInsensitive() {
        var phonebook = new Phonebook();
        phonebook.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );

        var result = phonebook.search("pEtR");
        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.size(), is(1));
    }
}
