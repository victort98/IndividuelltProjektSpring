package com.example.demo.controllers;

import com.example.demo.entities.Pokemon;
import com.example.demo.services.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Find pokemons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the pokemon/s"),
            @ApiResponse(responseCode = "400", description = "Invalid", content = @Content)
    })
    public ResponseEntity<List<Pokemon>> findPokemon (@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String type,
                                                      @RequestParam(required = false) String ability,
                                                      @RequestParam(required = false) String move) {
        var pokemon = pokemonService.findPokemon(name, type, ability, move);
        if(pokemon.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pokemon);
        }
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find pokemon by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the pokemon"),
            @ApiResponse(responseCode = "404", description = "No pokemon found with that id.", content = @Content)
    })
    public ResponseEntity<Pokemon> findPokemonById (@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Save pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added pokemon."),
            @ApiResponse(responseCode = "401", description = "Only admins can add pokemons.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid.", content = @Content)
    })
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        var savedPokemon = pokemonService.save(pokemon);
        var uri = URI.create("/api/v1/pokemon" + savedPokemon.getId());
        return ResponseEntity.created(uri).body(savedPokemon);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Update pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated pokemon."),
            @ApiResponse(responseCode = "401", description = "Only admins can update pokemons.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Delete pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted pokemon."),
            @ApiResponse(responseCode = "401", description = "Only admins can delete pokemons.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No pokemon found.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }
}
