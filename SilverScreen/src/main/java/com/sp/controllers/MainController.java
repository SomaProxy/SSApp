package com.sp.controllers;

import com.sp.entity.Human;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainController extends HumanController {

    @FXML
    private TreeTableView<Human> tableMainTree;

    @FXML
    private TreeTableColumn<Human, String> nameTableColumn;

    @FXML
    private TreeTableColumn<Human, Integer> ageTableColumn;

    @FXML
    private TreeTableColumn<Human, LocalDate> birthdayTableColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private TreeItem<Human> currentHuman;

    @FXML
    void initialize() {

        tableMainTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             if (newValue != null) {
                currentHuman = newValue;
            }
        });

        nameTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("age"));
        birthdayTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthday"));

        root.getChildren().setAll(humansList);
        tableMainTree.setRoot(root);
        tableMainTree.setShowRoot(false);

        tableMainTree.setOnMouseClicked(event ->  {
            if (event.getButton().equals(MouseButton.PRIMARY) &&
                    event.getClickCount() == 2 && currentHuman != null &&
                    currentHuman.getValue().getBirthday().getDayOfYear() == LocalDate.now().getDayOfYear()) {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("HB!");
                success.setHeaderText(null);
                success.setContentText("Congratulate " + currentHuman.getValue().getName());
                success.showAndWait();
            }
        });

        addButton.setOnAction(event -> {
            addButtonAction();
            root.getChildren().setAll(humansList);
        });
        editButton.setOnAction(event -> {
            editButtonAction();
            root.getChildren().setAll(humansList);
        });
        deleteButton.setOnAction(event -> {
            deleteButtonAction();
            root.getChildren().setAll(humansList);
        });
    }

    private void addButtonAction() {
        Stage stage = new Stage();
        stage.setTitle("Add human");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEntity.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();

    }

    private void editButtonAction() {
        if (currentHuman == null) {
            unselectedWarn();
        } else {
            Stage stage = new Stage();
            stage.setTitle("Edit human");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editEntity.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditController controller = loader.getController();
            controller.setEntity(currentHuman.getValue());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

    private void deleteButtonAction() {
        if (currentHuman == null) {
            unselectedWarn();
        } else {
            humansList.remove(currentHuman);
            currentHuman = null;
        }
    }

    private void unselectedWarn() {
        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setTitle("Warning!");
        warn.setHeaderText(null);
        warn.setContentText("Line is not selected.");
        warn.showAndWait();
    }
}