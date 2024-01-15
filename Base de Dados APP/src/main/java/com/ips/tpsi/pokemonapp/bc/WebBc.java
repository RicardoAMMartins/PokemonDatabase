package com.ips.tpsi.pokemonapp.bc;

import com.ips.tpsi.pokemonapp.Repository.PokemonRepository;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import com.ips.tpsi.pokemonapp.entity.PokemonTypeLvl;
import com.ips.tpsi.pokemonapp.entity.PokemonTypeLvlKey;
import com.ips.tpsi.pokemonapp.entity.TypePokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ips.tpsi.pokemonapp.Repository.TypeRepository;
import com.ips.tpsi.pokemonapp.Repository.PokemonTypeLvlRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class WebBc {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonTypeLvlRepository pokemonTypeLvlRepository;

    @Autowired
    private TypeRepository typeRepository;

    public List<Pokemon> getAllPokemonWithTypes() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        pokemonList = pokemonList.stream()
                .filter(Pokemon::getIsActive)
                .collect(Collectors.toList());
        for (Pokemon pokemon : pokemonList) {
            List<PokemonTypeLvl> typeLevels = pokemonTypeLvlRepository.findByPokemon(pokemon);
            if (!typeLevels.isEmpty()) {
                TypePokemon firstType = typeLevels.get(0).getTypePokemon();
                pokemon.setFirstType(firstType != null ? firstType : null);

                if (typeLevels.size() > 1) {
                    TypePokemon secondType = typeLevels.get(1).getTypePokemon();
                    pokemon.setSecondType(secondType != null ? secondType : null);
                } else {
                    pokemon.setSecondType(null);
                }
            } else {
                pokemon.setFirstType(null);
                pokemon.setSecondType(null);
            }
        }
        return pokemonList;
    }

    public void addPokemon(Pokemon newPokemon, Integer type1Id, Integer type2Id) {
        try {
            pokemonRepository.save(newPokemon);

            TypePokemon type1 = typeRepository.findById(type1Id).orElse(null);
            TypePokemon type2 = typeRepository.findById(type2Id).orElse(null);

            PokemonTypeLvl pokemonTypeLvl1 = new PokemonTypeLvl(new PokemonTypeLvlKey(newPokemon.getIdPokemon(), type1Id), newPokemon, type1, 1);
            PokemonTypeLvl pokemonTypeLvl2 = new PokemonTypeLvl(new PokemonTypeLvlKey(newPokemon.getIdPokemon(), type2Id), newPokemon, type2, 2);

            pokemonTypeLvlRepository.save(pokemonTypeLvl1);
            pokemonTypeLvlRepository.save(pokemonTypeLvl2);

            newPokemon.setIsActive(true);
            pokemonRepository.save(newPokemon);

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void editPokemon(Pokemon editedPokemon, Integer type1Id, Integer type2Id) {
        try {
            PokemonTypeLvlKey key1 = new PokemonTypeLvlKey(editedPokemon.getIdPokemon(), type1Id);
            PokemonTypeLvlKey key2 = new PokemonTypeLvlKey(editedPokemon.getIdPokemon(), type2Id);

            Optional<PokemonTypeLvl> existingPokemonTypeLvl1 = pokemonTypeLvlRepository.findById(key1);
            Optional<PokemonTypeLvl> existingPokemonTypeLvl2 = pokemonTypeLvlRepository.findById(key2);

            if (existingPokemonTypeLvl1.isPresent()) {
                PokemonTypeLvl pokemonTypeLvl1 = existingPokemonTypeLvl1.get();
                pokemonTypeLvl1.setTypePokemon(typeRepository.findById(type1Id).orElse(null));
                pokemonTypeLvlRepository.save(pokemonTypeLvl1);
            } else {
                PokemonTypeLvl pokemonTypeLvl1 = new PokemonTypeLvl(key1, editedPokemon, typeRepository.findById(type1Id).orElse(null), 1);
                pokemonTypeLvlRepository.save(pokemonTypeLvl1);
            }

            if (existingPokemonTypeLvl2.isPresent()) {
                PokemonTypeLvl pokemonTypeLvl2 = existingPokemonTypeLvl2.get();
                pokemonTypeLvl2.setTypePokemon(typeRepository.findById(type2Id).orElse(null));
                pokemonTypeLvlRepository.save(pokemonTypeLvl2);
            } else {
                PokemonTypeLvl pokemonTypeLvl2 = new PokemonTypeLvl(key2, editedPokemon, typeRepository.findById(type2Id).orElse(null), 2);
                pokemonTypeLvlRepository.save(pokemonTypeLvl2);
            }

            editedPokemon.setIsActive(true);
            pokemonRepository.save(editedPokemon);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    

    public void deletePokemon(Integer idPokemon) {
        Pokemon pokemonToDelete = getPokemonById(idPokemon);
        pokemonToDelete.setIsActive(false);
        pokemonRepository.save(pokemonToDelete);
    }

    public List<TypePokemon> getAllTypes() {
        List<TypePokemon> types = typeRepository.findAll();
        System.out.println("Types: " + types);
        return types;
    }

    public Pokemon getPokemonById(Integer id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        return optionalPokemon.orElse(null);
    }
}
