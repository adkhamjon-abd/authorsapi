package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adamjonson.authorsapi.api.OpenLibraryApi;
import com.adamjonson.authorsapi.repo.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public Author findAuthorByName(String name) {
        Author author = authorRepository.findByName(name);
        if (author != null) {
            return author;
        } else {
            Author externalAuthor = OpenLibraryApi.fetchAuthorByName(name);
            if (externalAuthor != null) {
                authorRepository.save(externalAuthor);
                return externalAuthor;
            } else {
                return null;
            }
        }

    }
}
