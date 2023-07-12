package com.example.vanzarepachetinternet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InternetPackage {
    private static int nextId = 1;
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty speed;
    private SimpleStringProperty bandwidth;
    private SimpleStringProperty duration;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty address;

    public InternetPackage(int speed, String bandwidth, String duration, String firstName, String lastName, String address) {
        this.id = new SimpleIntegerProperty(nextId++);
        this.speed = new SimpleIntegerProperty(speed);
        this.bandwidth = new SimpleStringProperty(bandwidth);
        this.duration = new SimpleStringProperty(duration);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getSpeed() {
        return speed.get();
    }

    public SimpleIntegerProperty speedProperty() {
        return speed;
    }

    public String getBandwidth() {
        return bandwidth.get();
    }

    public SimpleStringProperty bandwidthProperty() {
        return bandwidth;
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }
}