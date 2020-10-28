package com.example.demo.controllers;

import com.example.demo.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PokemonController {

    @Autowired
    PokemonService pokemonService;
}
