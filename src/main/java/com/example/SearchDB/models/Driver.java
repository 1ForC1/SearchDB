package com.example.SearchDB.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max = 50, message = "Размер данного поля должен быть в диапозене от 2 до 50")
    private String firstName, secondName, middleName, birthday;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    @JoinTable(name="driver_firm",
            joinColumns=@JoinColumn(name="driver_id"),
            inverseJoinColumns=@JoinColumn(name="firm_id"))
    private List<Firm> firms;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="pasport_id")
    private Pasport pasport;

    public List<Firm> getFirms() {
        return firms;
    }

    public void setFirms(List<Firm> firms) {
        this.firms = firms;
    }

    public Pasport getPasport() {
        return pasport;
    }

    public void setPasport(Pasport pasport) {
        this.pasport = pasport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    public Driver(String firstName, String secondName, String middleName, String birthday, Address address, Pasport pasport) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.address = address;
        this.pasport = pasport;
    }

    public Driver() {
    }
}
