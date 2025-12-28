package com.literalura_core.infrastructure.adapter.out.gutendex.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor (
    @JsonAlias("name" ) String nombre,
    @JsonAlias("birth_year" ) Integer fechaNacimiento,
    @JsonAlias("death_year" ) Integer fechaFallecimiento
) {}
