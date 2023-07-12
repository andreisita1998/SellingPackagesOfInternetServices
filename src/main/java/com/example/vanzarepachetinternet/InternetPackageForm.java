package com.example.vanzarepachetinternet;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class InternetPackageForm extends Application {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField addressField;
    private ToggleGroup speedToggleGroup;
    private ToggleGroup bandwidthToggleGroup;
    private ToggleGroup durationToggleGroup;
    private TableView<InternetPackage> tableView;
    private SalesManager salesManager;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Înregistrare vânzare pachet internet");

        salesManager = new SalesManager();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        Label firstNameLabel = new Label("First name:");
        grid.add(firstNameLabel, 0, 2);

        firstNameField = new TextField();
        grid.add(firstNameField, 1, 2);

        Label lastNameLabel = new Label("Last name:");
        grid.add(lastNameLabel, 0, 1);

        lastNameField = new TextField();
        grid.add(lastNameField, 1, 1);

        Label addressLabel = new Label("Address:");
        grid.add(addressLabel, 0, 0);

        addressField = new TextField();
        grid.add(addressField, 1, 0);


        Label speedLabel = new Label("Speed (mb/s):");
        grid.add(speedLabel, 0, 3);

        HBox speedButtonGroup = new HBox(5);
        speedToggleGroup = new ToggleGroup();

        RadioButton speed2Button = new RadioButton("2");
        speed2Button.setToggleGroup(speedToggleGroup);

        RadioButton speed5Button = new RadioButton("5");
        speed5Button.setToggleGroup(speedToggleGroup);

        RadioButton speed10Button = new RadioButton("10");
        speed10Button.setToggleGroup(speedToggleGroup);

        RadioButton speed20Button = new RadioButton("20");
        speed20Button.setToggleGroup(speedToggleGroup);

        RadioButton speed50Button = new RadioButton("50");
        speed50Button.setToggleGroup(speedToggleGroup);

        RadioButton speed100Button = new RadioButton("100");
        speed100Button.setToggleGroup(speedToggleGroup);

        speedButtonGroup.getChildren().addAll(speed2Button, speed5Button, speed10Button, speed20Button, speed50Button, speed100Button);
        grid.add(speedButtonGroup, 1, 3);

        Label bandwidthLabel = new Label("Bandwidth:");
        grid.add(bandwidthLabel, 0, 4);

        HBox bandwidthButtonGroup = new HBox(5);
        bandwidthToggleGroup = new ToggleGroup();

        RadioButton bandwidth1Button = new RadioButton("1");
        bandwidth1Button.setToggleGroup(bandwidthToggleGroup);

        RadioButton bandwidth5Button = new RadioButton("5");
        bandwidth5Button.setToggleGroup(bandwidthToggleGroup);

        RadioButton bandwidth10Button = new RadioButton("10");
        bandwidth10Button.setToggleGroup(bandwidthToggleGroup);

        RadioButton bandwidth100Button = new RadioButton("100");
        bandwidth100Button.setToggleGroup(bandwidthToggleGroup);

        RadioButton bandwidthFlatButton = new RadioButton("Flat");
        bandwidthFlatButton.setToggleGroup(bandwidthToggleGroup);

        bandwidthButtonGroup.getChildren().addAll(bandwidth1Button, bandwidth5Button, bandwidth10Button, bandwidth100Button, bandwidthFlatButton);
        grid.add(bandwidthButtonGroup, 1, 4);


        Label durationLabel = new Label("Duration:");
        grid.add(durationLabel, 0, 5);

        HBox durationButtonGroup = new HBox(5);
        durationToggleGroup = new ToggleGroup();

        RadioButton duration1YearButton = new RadioButton("1 year");
        duration1YearButton.setToggleGroup(durationToggleGroup);

        RadioButton duration2YearsButton = new RadioButton("2 years");
        duration2YearsButton.setToggleGroup(durationToggleGroup);

        durationButtonGroup.getChildren().addAll(duration1YearButton, duration2YearsButton);
        grid.add(durationButtonGroup, 1, 5);


        Button savePackageButton = new Button("Save Package");
        grid.add(savePackageButton, 1, 6);
        savePackageButton.setOnAction(event -> savePackage());


        Button clearFormButton = new Button("Clear Form");
        grid.add(clearFormButton, 0, 6);
        clearFormButton.setOnAction(event -> clearForm());


        Button deleteTableRowButton = new Button("Delete Table Row");
        grid.add(deleteTableRowButton, 2, 6);
        deleteTableRowButton.setOnAction(event -> deleteTableRow());


        tableView = new TableView<>();
        TableColumn<InternetPackage, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<InternetPackage, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<InternetPackage, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<InternetPackage, Integer> speedColumn = new TableColumn<>("Speed (mb/s)");
        speedColumn.setCellValueFactory(cellData -> cellData.getValue().speedProperty().asObject());

        TableColumn<InternetPackage, String> bandwidthColumn = new TableColumn<>("Bandwidth");
        bandwidthColumn.setCellValueFactory(cellData -> cellData.getValue().bandwidthProperty());


        TableColumn<InternetPackage, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, addressColumn, speedColumn, bandwidthColumn, durationColumn);
        tableView.setPlaceholder(new Label("No content in table"));

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(grid, tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void savePackage() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();

        RadioButton selectedSpeedButton = (RadioButton) speedToggleGroup.getSelectedToggle();
        RadioButton selectedBandwidthButton = (RadioButton) bandwidthToggleGroup.getSelectedToggle();
        RadioButton selectedDurationButton = (RadioButton) durationToggleGroup.getSelectedToggle();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() ||
                selectedSpeedButton == null || selectedBandwidthButton == null || selectedDurationButton == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Package Details");
            alert.setContentText("Please fill in all the required package details.");
            alert.showAndWait();
        } else {
            int speed = Integer.parseInt(selectedSpeedButton.getText());
            String bandwidth = selectedBandwidthButton.getText();
            String duration = selectedDurationButton.getText();

            InternetPackage internetPackage = new InternetPackage(speed, bandwidth, duration, firstName, lastName, address);

            salesManager.addSale(internetPackage);
            tableView.setItems(FXCollections.observableArrayList(salesManager.getSoldPackages()));

            clearForm();
        }
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        speedToggleGroup.selectToggle(null);
        bandwidthToggleGroup.selectToggle(null);
        durationToggleGroup.selectToggle(null);
    }

    private void deleteTableRow() {
        InternetPackage selectedPackage = tableView.getSelectionModel().getSelectedItem();
        if (selectedPackage != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Table Row");
            alert.setContentText("Are you sure you want to delete this row?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                salesManager.deleteSale(selectedPackage);
                tableView.setItems(FXCollections.observableArrayList(salesManager.getSoldPackages()));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Row Selected");
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}