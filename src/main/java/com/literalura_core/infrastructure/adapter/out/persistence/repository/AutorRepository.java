package com.literalura_core.infrastructure.adapter.out.persistence.repository;

import com.literalura_core.infrastructure.adapter.out.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNombre(String nombre);
    @Query("SELECT a FROM AutorEntity a WHERE a.nacimiento <= :anio AND (a.fallecimiento IS NULL OR a.fallecimiento >= :anio)")
    List<AutorEntity> autoresVivosEnAnio(int anio);
}