package ru.job4j.gc;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TxtFilesCacheTest {
    public static final String RESOURCE_NAME = "/dir";
    public static final String FILE_1_TXT = "file1.txt";
    public static final String TEXT_IN_FILE_1_TXT = "text in file1.txt";
    public static final String FILE_2_TXT = "file2.txt";
    public static final String TEXT_IN_FILE_2_TXT = "text in file2.txt";
    public static final int INT_0 = 0;
    public static final int INT_1 = 1;
    public static final int INT_2 = 2;

    @Test
    public void whenGetOneFileFromCacheThenContentIsCorrect() {
        String directory = this.getClass().getResource(RESOURCE_NAME).getFile();
        Cache cache = new TxtFilesCache(directory);

        String content = cache.get(FILE_1_TXT);

        assertThat(content, is(TEXT_IN_FILE_1_TXT));
    }

    @Test
    public void whenGetTwoFilesFromCacheThenContentsAreCorrect() {
        String directory = this.getClass().getResource(RESOURCE_NAME).getFile();
        Cache cache = new TxtFilesCache(directory);

        String content1 = cache.get(FILE_1_TXT);
        String content2 = cache.get(FILE_2_TXT);

        assertThat(content1, is(TEXT_IN_FILE_1_TXT));
        assertThat(content2, is(TEXT_IN_FILE_2_TXT));
    }

    @Test
    public void whenGetOneFileFromEmptyCacheThenSizeIncreasesFromZeroToOne() {
        String directory = this.getClass().getResource(RESOURCE_NAME).getFile();
        Cache cache = new TxtFilesCache(directory);

        int sizeBefore = cache.size();
        cache.get(FILE_1_TXT);
        int sizeAfter = cache.size();

        assertThat(sizeBefore, is(INT_0));
        assertThat(sizeAfter, is(INT_1));
    }

    @Test
    public void whenGetSameFileFromEmptyCacheTwiceThenSizeIncreasesFromZeroToOne() {
        String directory = this.getClass().getResource(RESOURCE_NAME).getFile();
        Cache cache = new TxtFilesCache(directory);

        int sizeBefore = cache.size();
        cache.get(FILE_1_TXT);
        cache.get(FILE_1_TXT);
        int sizeAfter = cache.size();

        assertThat(sizeBefore, is(INT_0));
        assertThat(sizeAfter, is(INT_1));
    }

    @Test
    public void whenGetTwoDifferentFilesFromEmptyCacheThenSizeIncreasesFromZeroToTwo() {
        String directory = this.getClass().getResource(RESOURCE_NAME).getFile();
        Cache cache = new TxtFilesCache(directory);

        int sizeBefore = cache.size();
        cache.get(FILE_1_TXT);
        cache.get(FILE_2_TXT);
        int sizeAfter = cache.size();

        assertThat(sizeBefore, is(INT_0));
        assertThat(sizeAfter, is(INT_2));
    }
}