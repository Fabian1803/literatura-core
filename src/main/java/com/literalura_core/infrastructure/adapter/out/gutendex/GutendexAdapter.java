package com.literalura_core.infrastructure.adapter.out.gutendex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura_core.domain.model.Libro;
import com.literalura_core.domain.ports.out.LibreriaExternaPort;
import com.literalura_core.infrastructure.adapter.out.gutendex.dto.DatosGutendex;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
@Component
public class GutendexAdapter implements LibreriaExternaPort {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String BASE_URL = "https://gutendex.com/books/?search=";
    @Override
    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        try {
            String url = BASE_URL + titulo.replace(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            DatosGutendex datosApi = objectMapper.readValue(response.body(), DatosGutendex.class);
            return datosApi.resultados().stream()
                    .findFirst()
                    .map(dto -> {
                        // Obtenemos el primer autor (o null si no hay)
                        var autorDto = dto.autores().isEmpty() ? null : dto.autores().get(0);
                        return new Libro(
                                dto.titulo(),
                                autorDto != null ? autorDto.nombre() : "Desconocido",
                                autorDto != null ? autorDto.fechaNacimiento() : null,
                                autorDto != null ? autorDto.fechaFallecimiento() : null,
                                dto.idiomas().isEmpty() ? "Desconocido" : dto.idiomas().get(0),
                                dto.numeroDeDescargas()
                        );
                    });
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar Gutendex", e);
        }
    }
}
