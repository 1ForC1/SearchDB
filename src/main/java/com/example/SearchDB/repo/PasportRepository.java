package com.example.SearchDB.repo;

import com.example.SearchDB.models.Pasport;
import org.springframework.data.repository.CrudRepository;

public interface PasportRepository extends CrudRepository<Pasport, Long> {
    Pasport findByNumber(String number);
}
