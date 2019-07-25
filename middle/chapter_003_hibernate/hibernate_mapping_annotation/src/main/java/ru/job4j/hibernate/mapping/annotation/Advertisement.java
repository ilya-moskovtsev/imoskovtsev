package ru.job4j.hibernate.mapping.annotation;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "adds")
public class Advertisement {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    private String make;
    private String model;
    private BigDecimal price;
    private boolean isNew;
    private LocalDate yearManufactured;
    private String transmission;
    private String body;
    private String engine;
    private int mileage;
    private double engineCapacity;
    private String drive;
    private String steeringWheel;
    private boolean isBroken;
    private int brakeHorsepower;
    private int numberOfOwners;
    private String color;
    private String photo;
    private boolean isSold;
    @ManyToOne
    private User author;
    private String text;
    private LocalDateTime posted;
    @ElementCollection
    @OneToMany
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public LocalDate getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(LocalDate yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        this.isBroken = broken;
    }

    public int getBrakeHorsepower() {
        return brakeHorsepower;
    }

    public void setBrakeHorsepower(int brakeHorsepower) {
        this.brakeHorsepower = brakeHorsepower;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPosted() {
        return posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
