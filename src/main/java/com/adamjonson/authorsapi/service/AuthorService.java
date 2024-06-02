package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.api.OpenLibraryApiAuthorDetails;
import com.adamjonson.authorsapi.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adamjonson.authorsapi.api.OpenLibraryApi;
import com.adamjonson.authorsapi.repo.AuthorRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    private final RestTemplate restTemplate;
    @Autowired
    public AuthorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String OPEN_LIBRARY_API_SEARCH_URL = "https://openlibrary.org/search/authors.json?q=";
    private static final String OPEN_LIBRARY_API_AUTHOR_URL = "https://openlibrary.org/authors/";

    public Author findAuthorByName(String name) {
        Author author = authorRepository.findByName(name);
        if (author != null) {
            return author;
        } else {
            // Call external API
            String url = OPEN_LIBRARY_API_SEARCH_URL + name;
            OpenLibraryApi response = restTemplate.getForObject(url, OpenLibraryApi.class);
            if (response != null && !response.getDocs().isEmpty()) {
                OpenLibraryApi.Doc doc = response.getDocs().get(0);

                // Fetch detailed author data using the key
                String authorDetailsUrl = OPEN_LIBRARY_API_AUTHOR_URL + doc.getKey() + ".json";
                OpenLibraryApiAuthorDetails authorDetails = restTemplate.getForObject(authorDetailsUrl, OpenLibraryApiAuthorDetails.class);

                if (authorDetails != null) {
                    Author fetchedAuthor = new Author();
                    fetchedAuthor.setName(authorDetails.getName());
                    fetchedAuthor.setOpenLibraryId(response.getDocs().get(0).getKey());
                    authorRepository.save(fetchedAuthor);
                    return fetchedAuthor;
                }
            }
            return null;
        }

    }
}
