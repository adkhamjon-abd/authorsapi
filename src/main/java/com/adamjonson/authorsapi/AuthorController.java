package com.adamjonson.authorsapi;

import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @GetMapping("/{name}")
    public Author getAuthorByName(@PathVariable String name) {
        return authorService.findAuthorByName(name);
    }

    @GetMapping("/{id}/works")
    public List<AuthorWork> getAuthorWorks(@PathVariable String id) {
        return authorService.findWorksByAuthorId(id);
    }
}
