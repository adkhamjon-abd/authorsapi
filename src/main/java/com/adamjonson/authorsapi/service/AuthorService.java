package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

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
