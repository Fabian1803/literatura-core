package com.literalura_core.infrastructure.adapter.in.web;

import com.literalura_core.application.service.LibroService;
import com.literalura_core.domain.model.Autor;
import com.literalura_core.domain.model.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable String titulo) {
        return libroService.buscar(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Libro> listarTodos() {
        return libroService.listarLibrosRegistrados();
    }

    @GetMapping("/idioma/{idioma}")
    public List<Libro> listarPorIdioma(@PathVariable String idioma) {
        return libroService.listarPorIdioma(idioma);
    }

    @GetMapping("/autores")
    public List<Autor> listarAutores() {
        return libroService.listarAutores();
    }

    @GetMapping("/autores-vivos/{anio}")
    public List<Autor> listarAutoresVivos(@PathVariable int anio) {
        return libroService.listarAutoresVivos(anio);
    }
}