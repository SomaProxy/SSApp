package com.sp.controllers;

import com.sp.entity.Human;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;

import java.time.LocalDate;

abstract class HumanController {

    static ObservableList<TreeItem<Human>> humansList = FXCollections.observableArrayList();
    TreeItem<Human> root = new TreeItem<>(new Human());

    Human saveHuman(String name, Integer age, LocalDate birthday, Human human) {
        if (name == null || name.isEmpty()) {
            showWarning("Print human name!");
        } else if (birthday == null) {
            showWarning("Print human date!");
        } else if (LocalDate.now().isBefore(birthday)) {
            showWarning("Time-Police been called");
        } else {
            human.setName(name);
            human.setBirthday(birthday);
            human.setAge(age);
            return human;
        }
        return null;
    }

    private void showWarning(String text) {
        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setTitle("Warning!");
        warn.setHeaderText(null);
        warn.setContentText(text);
        warn.showAndWait();
    }

}
