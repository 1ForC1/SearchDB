package com.example.SearchDB.models;


import javax.persistence.*;

@Entity
public class Pasport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String series;
    private String number;
    @OneToOne(optional = true, mappedBy = "pasport")
    private Driver owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Driver getOwner() {
        return owner;
    }

    public void setOwner(Driver owner) {
        this.owner = owner;
    }
}
