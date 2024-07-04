package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libro(

        @JsonAlias("id") String id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<Autor> autor,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Integer descargas
        ) {
}
