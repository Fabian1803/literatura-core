package com.literalura_core.infrastructure.adapter.out.persistence;
import com.literalura_core.domain.model.Autor;
import com.literalura_core.domain.model.Libro;
import com.literalura_core.domain.ports.out.LibroRepositoryPort;
import com.literalura_core.infrastructure.adapter.out.persistence.entity.AutorEntity;
import com.literalura_core.infrastructure.adapter.out.persistence.entity.LibroEntity;
import com.literalura_core.infrastructure.adapter.out.persistence.repository.AutorRepository;
import com.literalura_core.infrastructure.adapter.out.persistence.repository.LibroRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LibroPersistenceAdapter implements LibroRepositoryPort {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroPersistenceAdapter(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public Libro guardar(Libro libroDominio) {
        Optional<LibroEntity> libroExistente = libroRepository.findByTitulo(libroDominio.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya existe, no se guardará duplicado: " + libroDominio.titulo());
            return libroDominio;
        }
        AutorEntity autorEntity = autorRepository.findByNombre(libroDominio.autor())
                .orElseGet(() -> {
                    AutorEntity nuevoAutor = new AutorEntity(
                            libroDominio.autor(),
                            libroDominio.autorNacimiento(),
                            libroDominio.autorFallecimiento()
                    );
                    return autorRepository.save(nuevoAutor);
                });
        LibroEntity libroEntity = new LibroEntity(
                libroDominio.titulo(),
                libroDominio.idioma(),
                libroDominio.descargas(),
                autorEntity
        );
        libroRepository.save(libroEntity);
        return libroDominio;
    }

    @Override
    public List<Libro> listarTodos() {
        return libroRepository.findAll().stream()
                .map(entity -> new Libro(
                        entity.getTitulo(),
                        entity.getAutor().getNombre(),
                        entity.getAutor().getNacimiento(),
                        entity.getAutor().getFallecimiento(),
                        entity.getIdioma(),
                        entity.getDescargas()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma).stream()
                .map(entity -> new Libro(
                        entity.getTitulo(),
                        entity.getAutor().getNombre(),
                        entity.getAutor().getNacimiento(),
                        entity.getAutor().getFallecimiento(),
                        entity.getIdioma(),
                        entity.getDescargas()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Autor> listarAutores() {
        return autorRepository.findAll().stream()
                .map(entity -> new Autor(
                        entity.getNombre(),
                        entity.getNacimiento(),
                        entity.getFallecimiento()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Autor> listarAutoresVivos(int anio) {
        return autorRepository.autoresVivosEnAnio(anio).stream()
                .map(entity -> new Autor(
                        entity.getNombre(),
                        entity.getNacimiento(),
                        entity.getFallecimiento()
                ))
                .collect(Collectors.toList());
    }
}