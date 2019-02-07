package com.sp.controllers;

import com.sp.entity.Human;
import com.sp.utility.AgeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddController extends HumanController {

    @FXML
    private Button addSaveButton;

    @FXML
    private Button addCloseButton;

    @FXML
    private TextField addTextField;

    @FXML
    private DatePicker addDateFiled;

    private Integer DateMedium() {
        LocalDate date = LocalDate.now();
        AgeManager dateManager = new AgeManager();
        return dateManager.getHumanAge(date, addDateFiled.getValue());
    }

    @FXML
    void initialize() {
        addSaveButton.setOnAction(event ->
                saveButtonAction());

        addCloseButton.setOnAction(event ->
                closeButtonAction());
    }

    private void saveButtonAction() {
        Human human = new Human();
        human = saveHuman(addTextField.getText(), DateMedium(), addDateFiled.getValue(), human);
        if (human != null) {
            humansList.add(new TreeItem<>(human));
            closeButtonAction();
        }
    }

    private void closeButtonAction() {
        Stage stage = (Stage) addCloseButton.getScene().getWindow();
        stage.close();
    }

}
