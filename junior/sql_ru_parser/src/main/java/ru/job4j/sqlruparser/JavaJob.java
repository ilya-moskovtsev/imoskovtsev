package ru.job4j.sqlruparser;

import java.util.Objects;

import com.google.common.base.Joiner;

public class JavaJob {
    private String name;
    private String text;
    private String link;

    public JavaJob() {
    }

    public JavaJob(String name, String text, String link) {
        this.name = name;
        this.text = text;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JavaJob javaJob = (JavaJob) o;
        return Objects.equals(name, javaJob.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return Joiner.on(System.lineSeparator()).join(name, text, link);
    }
}
