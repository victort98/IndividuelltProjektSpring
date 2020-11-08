package com.example.demo.services;

import com.example.demo.dto.PokemonDto;
import com.example.demo.entities.ListOfPokemon;
import com.example.demo.repositories.GeneralInfoRepository;
import com.example.demo.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

@Service
public class PokemonConsumerService {

    @Autowired
    private GeneralInfoRepository generalInfoRepository;

    private final RestTemplate restTemplate;


    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto search(String name){
        var pokemon = generalInfoRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon"));
        var pokemons = restTemplate.getForObject(pokemon.url,PokemonDto.class);
        if(pokemons != null){
            pokemons.setName(pokemon.name);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemons");
        }
        return pokemons;
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
