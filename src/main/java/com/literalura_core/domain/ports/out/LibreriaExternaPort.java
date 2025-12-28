package com.literalura_core.domain.ports.out;
import com.literalura_core.domain.model.Libro;
import java.util.Optional;
public interface LibreriaExternaPort {
    Optional<Libro> buscarLibroPorTitulo(String titulo);
}
