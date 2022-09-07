package com.example.SearchDB.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand, model, body, transmission;

    private int power, engineСapacity;

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

    public int getEngineСapacity() {
        return engineСapacity;
    }

    public void setEngineСapacity(int engineСapacity) {
        this.engineСapacity = engineСapacity;
    }

    public Car(String brand, String model, String body, String transmission, int power, int engineСapacity) {
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.transmission = transmission;
        this.power = power;
        this.engineСapacity = engineСapacity;
    }

    public Car() {

    }
}
