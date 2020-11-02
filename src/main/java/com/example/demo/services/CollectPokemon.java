package com.example.demo.services;

import com.example.demo.entities.ListOfPokemon;
import com.example.demo.repositories.GeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class CollectPokemon {

    @Autowired
    public GeneralInfoRepository generalInfoRepository;

    private final RestTemplate restTemplate;

    public CollectPokemon(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostConstruct
    public void savePokemon() {
        var pokeapiURL = "https://pokeapi.co/api/v2/pokemon?limit=2000&offset=0";
        var pokemons = restTemplate.getForObject(pokeapiURL, ListOfPokemon.class);
        if(pokemons != null) {
            var pokemonList = pokemons.getResults();
            var pokemonInDb = generalInfoRepository.findAll();
            if(pokemonInDb.isEmpty()) {
                generalInfoRepository.saveAll(pokemonList);
            }
        }
    }
}
