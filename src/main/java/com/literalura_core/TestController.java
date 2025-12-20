package com.literalura_core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @GetMapping("/test-connection")
    public String probarConexion() {
        // 1. Spring llama a Gutendex
        String url = "https://gutendex.com/books/?search=quijote";
        RestTemplate restTemplate = new RestTemplate();
        String jsonRespuesta = restTemplate.getForObject(url, String.class);

        // 2. Retornamos el JSON crudo al Front
        return jsonRespuesta;
    }
}