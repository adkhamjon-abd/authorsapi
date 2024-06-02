package com.adamjonson.authorsapi.controller;

import com.adamjonson.authorsapi.model.Author;
import com.adamjonson.authorsapi.model.AuthorWork;
import com.adamjonson.authorsapi.service.AuthorService;
import com.adamjonson.authorsapi.service.AuthorWithWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public AuthorWithWorks getAuthorWorks(@PathVariable String id) {
        return authorService.findWorksByAuthorId(id);
    }
}
