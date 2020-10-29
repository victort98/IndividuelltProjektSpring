package com.example.demo.services;

import com.example.demo.dto.PokemonDto;
import com.example.demo.entities.Pokemon;
import com.example.demo.repositories.GeneralInfoRepository;
import com.example.demo.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private GeneralInfoRepository generalInfoRepository;

    @Autowired
    private PokemonConsumerService pokemonConsumerService;


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
            return getPokemonAndSave(name);
    }

    public Pokemon findById(String id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the pokemon"));
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void update(String id, Pokemon pokemon) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a pokemon with that id");
        }
        pokemon.setId(id);
        pokemonRepository.save(pokemon);
    }

    public void delete(String id) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a pokemon with that id");
        }
        pokemonRepository.deleteById(id);
    }

    private Boolean pokemonInDb (PokemonDto pokemonDto) {
        var pokemonAlreadyInDb = pokemonRepository.findAll();
        pokemonAlreadyInDb = pokemonAlreadyInDb.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().equals(pokemonDto.getName()))
                .collect(Collectors.toList());
        return !pokemonAlreadyInDb.isEmpty();
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

    private List<Pokemon> getPokemonAndSave (String name) {
        var pokemons = generalInfoRepository.findAll();
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        var pokemonsWithInfo = new ArrayList<Pokemon>();

        pokemons.forEach(pokemon -> {
            var pokemonDto = pokemonConsumerService.search(name);
            var pokemonWithInfo = new Pokemon(pokemonDto.getName(), pokemonDto.getHeight(), pokemonDto.getWeight(),
                    pokemonDto.getBaseExperience(), pokemonDto.getLocationEncounter(),pokemonDto.getTypes(),
                    pokemonDto.getAbilities(), pokemonDto.getGames(), pokemonDto.getSpecie());

            var pokemonInDb = pokemonInDb(pokemonDto);
            if(!pokemonInDb) {
                this.save(pokemonWithInfo);
            }
            pokemonsWithInfo.add(pokemonWithInfo);
        });
        return pokemonsWithInfo;
    }

}
