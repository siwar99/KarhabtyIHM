package com.example.karhabty;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Lavage",
                "Réparation",
                "Réclamation"
        );

        Button serviceClientButton = new Button("Service Client");
        VBox root = new VBox(serviceClientButton);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);

        ListView<String> listView = new ListView<>(list);

        serviceClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().add(listView);
            }
        });

        listView.setOnMouseClicked(event -> {
            String selectedService = listView.getSelectionModel().getSelectedItem();
            if ("Lavage".equals(selectedService)) {
                showDateTimePickerPopup(primaryStage);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDateTimePickerPopup(Stage primaryStage) {
        Stage dateTimeStage = new Stage();
        dateTimeStage.setTitle("Lavage date and timer picker");

        // Create a DatePicker for selecting date
        DatePicker datePicker = new DatePicker();

        // Create a Spinner for selecting time
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 12);
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);

        VBox dateTimeBox = new VBox(datePicker, hourSpinner, minuteSpinner);
        dateTimeBox.setAlignment(Pos.CENTER);
        dateTimeBox.setSpacing(10);

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            LocalTime selectedTime = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
            System.out.println("Selected Date and Time: " + selectedDate + " " + selectedTime);

            // You can perform actions with the selected date and time here

            dateTimeStage.close();
        });

        dateTimeBox.getChildren().add(confirmButton);

        Scene dateTimeScene = new Scene(dateTimeBox, 300, 200);

        dateTimeStage.setScene(dateTimeScene);
        dateTimeStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
