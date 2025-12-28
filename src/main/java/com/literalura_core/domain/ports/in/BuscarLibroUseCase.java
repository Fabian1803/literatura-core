package com.literalura_core.domain.ports.in;
import com.literalura_core.domain.model.Libro;
import java.util.Optional;
public interface BuscarLibroUseCase {
    Optional<Libro> buscar(String titulo);
}