package com.example.SearchDB.repo;

import com.example.SearchDB.models.Firm;
import org.springframework.data.repository.CrudRepository;

public interface FirmRepository extends CrudRepository<Firm, Long> {
    Firm findByName(String name);
}
