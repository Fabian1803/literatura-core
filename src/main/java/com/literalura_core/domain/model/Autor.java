package com.literalura_core.domain.model;

public record Autor(
        String nombre,
        Integer nacimiento,
        Integer fallecimiento
) {
    @Override
    public String toString() {
        return nombre + " (" + nacimiento + "-" + fallecimiento + ")";
    }
}