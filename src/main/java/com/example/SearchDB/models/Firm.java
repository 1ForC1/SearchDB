package com.example.SearchDB.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name="driver_firm",
            joinColumns=@JoinColumn(name="firm_id"),
            inverseJoinColumns=@JoinColumn(name="driver_id"))
    private List<Driver> drivers;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}