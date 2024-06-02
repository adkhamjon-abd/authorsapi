package com.adamjonson.authorsapi.repo;

import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.model.AuthorWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorWorkRepository extends JpaRepository<AuthorWork, Integer> {
    List<AuthorWork> findByAuthorId(String authorId);

}
