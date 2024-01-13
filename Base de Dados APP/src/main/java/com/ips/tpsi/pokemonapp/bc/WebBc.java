package com.ips.tpsi.pokemonapp.bc;

import com.ips.tpsi.pokemonapp.Repository.PokemonRepository;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebBc {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    public void addPokemon(Pokemon newPokemon) {
        pokemonRepository.save(newPokemon);
    }

    public void editPokemon(Pokemon editedPokemon) {
        pokemonRepository.save(editedPokemon);
    }

    public void deletePokemon(Integer idPokemon) {
        pokemonRepository.deleteById(idPokemon);
    }

    public Pokemon getPokemonById(Integer id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        return optionalPokemon.orElse(null);
    }
}
