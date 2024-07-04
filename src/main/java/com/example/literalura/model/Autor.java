package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaMuerte
) {
}
