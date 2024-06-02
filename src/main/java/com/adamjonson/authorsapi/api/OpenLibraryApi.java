package com.adamjonson.authorsapi.api;

import com.adamjonson.authorsapi.model.Author;
import org.springframework.stereotype.Component;

@Component
public class OpenLibraryApi {
    public static Author fetchAuthorByName(String name) {
        return new Author();
    }
}
