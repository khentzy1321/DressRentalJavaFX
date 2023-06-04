package com.example.dresscasualrents;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import java.time.LocalDate;


public class DressController {

    @FXML
    private TableView<Dress> dressTable;
    @FXML
    private TableColumn<Dress, String> nameColumn;
    @FXML
    private TableColumn<Dress, String> addressColumn;
    @FXML
    private TableColumn<Dress, String> dressTypeColumn;
    @FXML
    private TableColumn<Dress, String> dateRentedColumn;
    @FXML
    private TableColumn<Dress, String> returnRentedColumn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> dressTypeField;
    @FXML
    private DatePicker dateRentedPicker;
    @FXML
    private DatePicker returnRentedPicker;

    private DressRepository dressRepository;

    public DressController() {
        dressRepository = new DressRepository();
    }

    @FXML
    private void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        dressTypeColumn.setCellValueFactory(cellData -> cellData.getValue().dressTypeProperty());
        dateRentedColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(
                () -> cellData.getValue().getDateRented().toString(),
                cellData.getValue().dateRentedProperty()));
        returnRentedColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(
                () -> cellData.getValue().getReturnRented().toString(),
                cellData.getValue().returnRentedProperty()));

        showDressDetails(null);


        dressTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDressDetails(newValue));
    }


    private void showDressDetails(Dress dress) {
        if (dress != null) {

            nameField.setText(dress.getName());
            addressField.setText(dress.getAddress());
            dressTypeField.setValue(dress.getDressType());
            dateRentedPicker.setValue(dress.getDateRented());
            returnRentedPicker.setValue(dress.getReturnRented());
        } else {

            nameField.setText("");
            addressField.setText("");
            dressTypeField.setValue("");
            dateRentedPicker.setValue(null);
            returnRentedPicker.setValue(null);
        }
    }

    @FXML
    private void handleNewDress() {

        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                dressTypeField.getValue().isEmpty() || dateRentedPicker.getValue() == null ||
                returnRentedPicker.getValue() == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return;
        }


        String name = nameField.getText();
        String address = addressField.getText();
        String dressType = dressTypeField.getValue();
        LocalDate dateRented = dateRentedPicker.getValue();
        LocalDate returnRented = returnRentedPicker.getValue();
        Dress newDress = new Dress(name, address, dressType, dateRented, returnRented);


        dressRepository.addDress(newDress);

        clearFields();
        updateTableView();
    }
    @FXML
    private void handleUpdateDress() {

        Dress selectedDress = dressTable.getSelectionModel().getSelectedItem();

        if (selectedDress == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Dress Selected");
            alert.setContentText("Please select a dress to update.");
            alert.showAndWait();
            return;
        }


        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                dressTypeField.getValue().isEmpty() || dateRentedPicker.getValue() == null ||
                returnRentedPicker.getValue() == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return;
        }


        selectedDress.setName(nameField.getText());
        selectedDress.setAddress(addressField.getText());
        selectedDress.setDressType(dressTypeField.getValue());
        selectedDress.setDateRented(dateRentedPicker.getValue());
        selectedDress.setReturnRented(returnRentedPicker.getValue());

        dressRepository.updateDress(selectedDress);


        clearFields();
        updateTableView();
    }

    @FXML
    private void handleDeleteDress() {

        Dress selectedDress = dressTable.getSelectionModel().getSelectedItem();

        if (selectedDress == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Dress Selected");
            alert.setContentText("Please select a dress to delete.");
            alert.showAndWait();
            return;
        }

        // Display a confirmation dialog before deleting the dress
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Dress");
        alert.setContentText("Are you sure you want to delete this dress?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            dressRepository.deleteDress(selectedDress);


            clearFields();
            updateTableView();
        }
    }


    private void clearFields() {
        nameField.clear();
        addressField.clear();
        dressTypeField.setValue(null);
        dateRentedPicker.setValue(null);
        returnRentedPicker.setValue(null);
    }

    private void updateTableView() {
        dressTable.setItems(dressRepository.getAllDresses());
    }
}
