package com.literalura_core.application.service;

import com.literalura_core.domain.model.Autor;
import com.literalura_core.domain.model.Libro;
import com.literalura_core.domain.ports.in.BuscarLibroUseCase;
import com.literalura_core.domain.ports.out.LibreriaExternaPort;
import com.literalura_core.domain.ports.out.LibroRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService implements BuscarLibroUseCase {
    private final LibreriaExternaPort libreriaPort;
    private final LibroRepositoryPort repositoryPort;

    public LibroService(LibreriaExternaPort libreriaPort, LibroRepositoryPort repositoryPort) {
        this.libreriaPort = libreriaPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<Libro> buscar(String titulo) {
        Optional<Libro> libroAPI = libreriaPort.buscarLibroPorTitulo(titulo);
        if (libroAPI.isPresent()) {
            var libro = libroAPI.get();
            repositoryPort.guardar(libro);
            System.out.println("Libro guardado en Postgres: " + libro.titulo());
        }
        return libroAPI;
    }

    public List<Libro> listarLibrosRegistrados() {
        return repositoryPort.listarTodos();
    }

    public List<Libro> listarPorIdioma(String idioma) {
        return repositoryPort.listarPorIdioma(idioma);
    }

    public List<Autor> listarAutores() {
        return repositoryPort.listarAutores();
    }

    public List<Autor> listarAutoresVivos(int anio) {
        return repositoryPort.listarAutoresVivos(anio);
    }
}