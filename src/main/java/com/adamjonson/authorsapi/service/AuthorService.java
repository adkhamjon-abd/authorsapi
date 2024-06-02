package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    public Author findAuthorByName(String name) {
        return new Author();
    }
}
