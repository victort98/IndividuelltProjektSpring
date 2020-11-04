package com.example.demo.controllers;

import com.example.demo.entities.Pokemon;
import com.example.demo.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemonByNameTypeHeightWeight (@RequestParam(required = false) String name,
                                                                            @RequestParam(required = false) String type,
                                                                            @RequestParam(required = false) String ability) {
        var pokemon = pokemonService.findPokemonByNameTypeHeightWeightAbility(name, type, ability);
        if(pokemon.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pokemon);
        }
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById (@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        var savedPokemon = pokemonService.save(pokemon);
        var uri = URI.create("/api/v1/pokemon" + savedPokemon.getId());
        return ResponseEntity.created(uri).body(savedPokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }
}
