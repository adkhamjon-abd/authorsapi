package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.model.AuthorWork;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;



@Data
public class AuthorWithWorks {
    private Author author;
    private List<AuthorWork> works;

    @Autowired
    public AuthorWithWorks(Author author, List<AuthorWork> works) {
        this.author = author;
        this.works = works;
    }
}
