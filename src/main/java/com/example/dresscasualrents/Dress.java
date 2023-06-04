package com.example.dresscasualrents;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Dress {
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty dressType;
    private final ObjectProperty<LocalDate> dateRented;
    private final ObjectProperty<LocalDate> returnRented;

    public Dress(String name, String address, String dressType, LocalDate dateRented, LocalDate returnRented) {
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.dressType = new SimpleStringProperty(dressType);
        this.dateRented = new SimpleObjectProperty<>(dateRented);
        this.returnRented = new SimpleObjectProperty<>(returnRented);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty dressTypeProperty() {
        return dressType;
    }

    public String getDressType() {
        return dressType.get();
    }

    public void setDressType(String dressType) {
        this.dressType.set(dressType);
    }

    public ObjectProperty<LocalDate> dateRentedProperty() {
        return dateRented;
    }

    public LocalDate getDateRented() {
        return dateRented.get();
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented.set(dateRented);
    }

    public ObjectProperty<LocalDate> returnRentedProperty() {
        return returnRented;
    }

    public LocalDate getReturnRented() {
        return returnRented.get();
    }

    public void setReturnRented(LocalDate returnRented) {
        this.returnRented.set(returnRented);
    }
}
