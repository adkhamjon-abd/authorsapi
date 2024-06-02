package com.adamjonson.authorsapi.api;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@Data
public class OpenLibraryApiAuthorDetails {
    private String key;
    private String name;
    private String personalName;
    private String birthDate;
    private String deathDate;
    private String topWork;
    private int workCount;
    private List<String> alternateNames;
    private List<Link> links;
    private String bio;

    @Data
    private static class Link {
        private String url;
        private Type type;
        private String title;
    }

    @Data
    private static class Type {
        private String key;
    }

}
