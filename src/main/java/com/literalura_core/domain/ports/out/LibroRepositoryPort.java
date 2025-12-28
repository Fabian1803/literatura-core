package com.literalura_core.domain.ports.out;

import com.literalura_core.domain.model.Autor;
import com.literalura_core.domain.model.Libro;
import java.util.List;

public interface LibroRepositoryPort {
    Libro guardar(Libro libro);
    List<Libro> listarTodos();
    List<Libro> listarPorIdioma(String idioma);
    List<Autor> listarAutores();
    List<Autor> listarAutoresVivos(int anio);
}