package com.sp.controllers;

import com.sp.entity.Human;
import com.sp.utility.AgeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EditController extends HumanController {

    private Human human;

    @FXML
    private Button editSaveButton;

    @FXML
    private Button editCloseButton;

    @FXML
    private TextField editNameField;

    @FXML
    private DatePicker editDateField;

    private Integer DateMedium() {
        LocalDate date = LocalDate.now();
        AgeManager dateManager = new AgeManager();
        return dateManager.getHumanAge(date, editDateField.getValue());
    }

    @FXML
    void initialize() {
        editCloseButton.setOnAction(event ->
                closeButtonAction()
        );
        editSaveButton.setOnAction(event ->
                saveButtonAction()
        );
    }

    private void closeButtonAction() {
        Stage stage = (Stage) editCloseButton.getScene().getWindow();
        stage.close();
        root.getChildren().setAll(humansList);

    }

    private void saveButtonAction() {
        Human human = saveHuman(editNameField.getText(), DateMedium(), (editDateField.getValue()), this.human);
        if (human != null) {
            this.human = human;
            closeButtonAction();
        }
    }

    void setEntity( Human human) {
        this.human = human;
        this.editNameField.textProperty().setValue(human.getName());
        this.editDateField.valueProperty().setValue(human.getBirthday());
    }

}