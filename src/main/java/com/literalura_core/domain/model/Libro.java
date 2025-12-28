package com.literalura_core.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Libro (
        @JsonProperty("title") String titulo,
        @JsonProperty("authors") String autor,
        @JsonProperty("birth_year") Integer autorNacimiento,
        @JsonProperty("death_year") Integer autorFallecimiento,
        @JsonProperty("languages") String idioma,
        @JsonProperty("download_count") double descargas
) {
    @Override
    public String toString() {
        return String.format("'%s' por %s (%s-%s) [%s]",
                titulo, autor,
                autorNacimiento != null ? autorNacimiento : "?",
                autorFallecimiento != null ? autorFallecimiento : "?",
                idioma);
    }
}