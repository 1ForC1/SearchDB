package com.example.SearchDB.repo;

import com.example.SearchDB.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findBySecondNameContains(String secondName);
    //List<User> findBySecondName(String secondName);
}
