package com.adamjonson.authorsapi.service;

import com.adamjonson.authorsapi.api.OpenLibraryApiAuthorDetails;
import com.adamjonson.authorsapi.api.OpenLibraryApiWorksResponse;
import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.model.AuthorWork;
import com.adamjonson.authorsapi.repo.AuthorWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adamjonson.authorsapi.api.OpenLibraryApi;
import com.adamjonson.authorsapi.repo.AuthorRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorWorkRepository authorWorkRepository;

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

    public AuthorWithWorks findWorksByAuthorId(String authorId) {
        Author author = authorRepository.findByOpenLibraryId(authorId);
        List<AuthorWork> works = authorWorkRepository.findByAuthorId(authorId);
        // Return works of the author with name if exists
        if (!works.isEmpty()) {
            return AuthorWithWorks(author, works);
        } else {
            // Fetch works from OpenLibrary API
            String url = OPEN_LIBRARY_API_AUTHOR_URL + authorId + "/works.json";
            OpenLibraryApiWorksResponse response = restTemplate.getForObject(url, OpenLibraryApiWorksResponse.class);
            if (response != null && response.getEntries() != null) {
                for (OpenLibraryApiWorksResponse.Entry entry : response.getEntries()) {
                    AuthorWork work = new AuthorWork();
                    work.setTitle(entry.getTitle());
                    work.setAuthorId(authorId);
                    authorWorkRepository.save(work);
                    works.add(work);
                }
                return AuthorWithWorks(author, works);
            } else {
                return null; // Return empty list if no works are found
            }
        }
    }
}
