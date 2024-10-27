package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired // Auto Dependecy Injection
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType()); // Create new model from coming dto datas (frontend)

        Pokemon newPokemon = pokemonRepository.save(pokemon); // Save new pokemon and take it

        PokemonDto pokemonResponse = new PokemonDto(); // Create new dto
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse; // Show created pokemon
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        // map because it returns a list
        return pokemon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }

    private PokemonDto mapToDto(Pokemon pokemon){ // Mapper function for converting pokemon model to pokemon dto
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){ // Mapper function for converting pokemon dto to pokemon model (entity)
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
