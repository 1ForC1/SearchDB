package com.example.SearchDB.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max = 50, message = "Размер данного поля должен быть в диапозене от 2 до 50")
    private String firstName, secondName, middleName, birthday;

    @NotNull(message = "Поле не может быть пустым")
    @PositiveOrZero(message = "Поле не может быть меньше нуля")
//    @Min(value = 1000, message = "Размер данного поля должен составлять 4 символа")
//    @Max(value = 9999, message = "Размер данного поля должен составлять 4 символа")
    @Digits(integer=4, fraction=0, message = "Не более 4-х знаков")
    private int passportSeries;

    @NotNull(message = "Поле не может быть пустым")
    @PositiveOrZero(message = "Поле не может быть меньше нуля")
//    @Min(value = 100000, message = "Размер данного поля должен составлять 6 символов")
//    @Max(value = 999999, message = "Размер данного поля должен составлять 6 символов")
    @Digits(integer=6, fraction=0, message = "Не более 6-х знаков")
    private int passportNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Driver(String firstName, String secondName, String middleName, String birthday, int passportSeries, int passportNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    public Driver() {
    }
}
