package com.example.SearchDB.repo;

import com.example.SearchDB.models.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Long> {

    List<Driver> findBySecondNameContains(String secondName);
    //List<User> findBySecondName(String secondName);
}
