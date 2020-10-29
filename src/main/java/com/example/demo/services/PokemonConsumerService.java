package com.example.demo.services;

import com.example.demo.dto.PokemonDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonConsumerService {

    private final RestTemplate restTemplate;
    private String url;

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PokemonDto search(String name) {
        var urlWithTitleQuery = url + "&n=" + name;

        var pokemon = restTemplate.getForObject(urlWithTitleQuery, PokemonDto.class);

        if (pokemon == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
        }
        return pokemon;
    }
}
