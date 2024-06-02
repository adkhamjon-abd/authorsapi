package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.model.AuthorWork;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
public class AuthorWithWorks {
    private Author author;
    private List<AuthorWork> works;

    public AuthorWithWorks(Author author, List<AuthorWork> works) {
        this.author = author;
        this.works = works;
    }
}
