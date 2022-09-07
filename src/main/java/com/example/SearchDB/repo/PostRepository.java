package com.example.SearchDB.repo;

import com.example.SearchDB.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

//    List<Post> findByTitle(String title);
    List<Post> findByTitleContains(String title);
}
