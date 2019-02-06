package ru.job4j.badwordsfilter;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BadWordsFilterTest {

    @Test
    public void filterBadWords() {

        var filter = new BadWordsFilter();

        filter.setBadWords(Set.of("badWord1", "badWord2", "badWord3"));

        var input = List.of("goodWord1 badWord1", "badWord1 goodWord2 badWord2", "goodWord3 badWord3 goodWord3");

        var expected = List.of("goodWord1 ********", "******** goodWord2 ********", "goodWord3 ******** goodWord3");

        var result = filter.filterBadWords(input);

        assertThat(result, is(expected));
    }
}