package com.example.SearchDB.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max = 50, message = "Размер данного поля должен быть в диапозене от 2 до 50")
    private String brand, model, body, transmission;

    @NotNull(message = "Поле не может быть пустым")
    @PositiveOrZero(message = "Поле не может быть меньше нуля")
//    @Min(value = 0, message = "Размер данного поля должен быть в диапозене от 0 до 100")
//    @Max(value = 100, message = "Размер данного поля должен быть в диапозене от 0 до 100")
    @Digits(integer=5, fraction=0, message = "Не более 5-х чисел")
    private int power, engineCapacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Car(String brand, String model, String body, String transmission, int power, int engineCapacity) {
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.transmission = transmission;
        this.power = power;
        this.engineCapacity = engineCapacity;
    }

    public Car() {

    }
}
