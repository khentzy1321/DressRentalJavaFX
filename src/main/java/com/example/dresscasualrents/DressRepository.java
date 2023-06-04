package com.example.dresscasualrents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DressRepository {
    private ObservableList<Dress> dressList;

    public DressRepository() {
        dressList = FXCollections.observableArrayList();
    }

    public void addDress(Dress dress) {
        dressList.add(dress);
    }

    public void updateDress(Dress dress) {

    }

    public void deleteDress(Dress dress) {
        dressList.remove(dress);
    }

    public ObservableList<Dress> getAllDresses() {
        return dressList;
    }
}
