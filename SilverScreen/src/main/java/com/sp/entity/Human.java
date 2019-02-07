package com.sp.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Human {

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

    public Human() {
    }

    public Human( String name, Integer age, LocalDate birthday) {
        this.name.set(name);
        this.age.set(age);
        this.birthday.set(birthday);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getAge() {
        return age.get();
    }

    public void setAge(Integer age) {
        this.age.set(age);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }


    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

}
