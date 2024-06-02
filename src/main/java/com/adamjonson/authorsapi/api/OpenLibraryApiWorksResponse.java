package com.adamjonson.authorsapi.api;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OpenLibraryApiWorksResponse {
    private List<Entry> entries;

    @Data
    public static class Entry {
        private String title;
    }
}
