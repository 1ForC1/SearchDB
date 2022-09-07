package com.example.SearchDB.repo;

import com.example.SearchDB.models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByBrandContains(String brand);
    //List<Car> findByBrand(String brand);
}
