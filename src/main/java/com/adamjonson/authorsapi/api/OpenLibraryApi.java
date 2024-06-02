package com.adamjonson.authorsapi.api;

import com.adamjonson.authorsapi.model.Author;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class OpenLibraryApi {
    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<Doc> docs;

    @Data
    public static class Doc {
        private String key;
        private String type;
        private String name;
        private List<String> alternateNames;
        private String birthDate;
        private String topWork;
        private int workCount;
        private List<String> topSubjects;
    }
}
