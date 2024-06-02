package com.adamjonson.authorsapi;

import com.adamjonson.authorsapi.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping("/{name}")
    public Author getAuthorByName(@PathVariable String name) {
        return new Author();
    }
}
