package ru.job4j.email;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emails")
public class Email {
    private int id;
    private String text;
    private boolean sent;

    public Email() {
    }

    public Email(String text, boolean sent) {
        this.text = text;
        this.sent = sent;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Email{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", sent=" + sent
                + '}';
    }
}
