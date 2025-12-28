package com.literalura_core.infrastructure.adapter.out.persistence.repository;

import com.literalura_core.infrastructure.adapter.out.persistence.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findByIdioma(String idioma);
    Optional<LibroEntity> findByTitulo(String titulo);
}