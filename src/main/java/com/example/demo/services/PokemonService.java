package com.example.demo.services;

import com.example.demo.dto.PokemonDto;
import com.example.demo.entities.ListOfPokemon;
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


    public List<Pokemon> findPokemon(String name, String type, String ability, String move) {
        var pokemon = pokemonRepository.findAll();
        if(type != null) {
            pokemon = filterPokemonsByType(type, pokemon);
        }
        if(name != null) {
            pokemon = filterPokemonsByName(name, pokemon);
        }
        if(ability != null) {
            pokemon = filterPokemonsByAbility(ability, pokemon);
        }
        if(move != null) {
            pokemon = filterPokemonsByMove(move, pokemon);
        }
            return pokemon;
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

    private Boolean pokemonInDbMatch (PokemonDto pokemonDto) {
        var pokemonAlreadyInDb = pokemonRepository.findAll();
        pokemonAlreadyInDb = pokemonAlreadyInDb.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().equals(pokemonDto.getName()))
                .collect(Collectors.toList());
        return !pokemonAlreadyInDb.isEmpty();
    }


    private List<Pokemon> filterPokemonsByName(String name, List<Pokemon> pokemons) {
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            pokemons = this.getPokemonAndSave(name);
        }
        if(pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that name");
        }
        return pokemons;
    }

    private List<Pokemon> filterPokemonsByType(String type, List<Pokemon> pokemons) {
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getTypes().stream().anyMatch(pokemonType -> pokemonType.getType().name.toLowerCase().contains(type)))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that type");
        }
        return pokemons;
    }

    private List<Pokemon> filterPokemonsByAbility(String ability, List<Pokemon> pokemons) {
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getAbilities().stream().anyMatch(pokemonAbility -> pokemonAbility.getAbility().name.toLowerCase().contains(ability)))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that ability");
        }
        return pokemons;
    }

    private List<Pokemon> filterPokemonsByMove(String move, List<Pokemon> pokemons) {
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getMoves().stream().anyMatch(pokemonMove -> pokemonMove.getMove().name.toLowerCase().contains(move)))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found with that move");
        }
        return pokemons;
    }

    private List<Pokemon> getPokemonAndSave (String name) {
        var pokemons = generalInfoRepository.findAll();
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        var pokemonsWithInfo = new ArrayList<Pokemon>();

        pokemons.forEach(pokemon -> {
            var pokemonDto = pokemonConsumerService.search(pokemon.getName());
            var pokemonWithInfo = new Pokemon(pokemonDto.getName(), pokemonDto.getHeight(), pokemonDto.getWeight(),
                    pokemonDto.getBaseExperience(), pokemonDto.getLocationEncounter(),pokemonDto.getTypes(),
                    pokemonDto.getAbilities(), pokemonDto.getMoves());

            var pokemonInDb = pokemonInDbMatch(pokemonDto);
            if(!pokemonInDb) {
                this.save(pokemonWithInfo);
            }
            pokemonsWithInfo.add(pokemonWithInfo);
        });
        return pokemonsWithInfo;
    }

}
