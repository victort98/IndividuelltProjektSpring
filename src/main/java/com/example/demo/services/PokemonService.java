package com.example.demo.services;

import com.example.demo.entities.Pokemon;
import com.example.demo.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;


    public List<Pokemon> findPokemonByNameAndOrType(String name, String type) {
        if(name != null && type != null) {
            var pokemonFromDB = pokemonNameAndTypeInDB(name, type);
            if(!pokemonFromDB.isEmpty()) {
                    return pokemonFromDB;
                }
            }
            else if(name != null) {
                var pokemonFromDB = pokemonNameInDB(name);
                if(!pokemonFromDB.isEmpty()) {
                    return pokemonFromDB;
                }
        }
            //return pokemon from api and save
    }

    public Pokemon findById(String id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the pokemon"));
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void updatePokemon(String id, Pokemon pokemon) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a pokemon with that id");
        }
        pokemon.setId(id);
        pokemonRepository.save(pokemon);
    }

    public void deletePokemon(String id) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a pokemon with that id");
        }
        pokemonRepository.deleteById(id);
    }

    private List<Pokemon> pokemonNameAndTypeInDB (String name, String type){
        var pokemonsInDB = pokemonRepository.findAll();
        pokemonsInDB = pokemonsInDB.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name))
                .filter(pokemon -> pokemon.getTypes().stream().anyMatch(pokemonType -> pokemonType.getType().name.toLowerCase().contains(type)))
                .collect(Collectors.toList());
        if(pokemonsInDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that name and type");
        }
        return pokemonsInDB;
    }

    private List<Pokemon> pokemonNameInDB (String name) {
        var pokemonInDB = pokemonRepository.findAll();
        pokemonInDB = pokemonInDB.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name))
                .collect(Collectors.toList());
        if(pokemonInDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that name");
        }
        return pokemonInDB;
    }

}
