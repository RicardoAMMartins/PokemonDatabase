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
            pokemonRepository.save(editedPokemon);

            TypePokemon type1 = typeRepository.findById(type1Id).orElse(null);
            TypePokemon type2 = typeRepository.findById(type2Id).orElse(null);

            PokemonTypeLvl pokemonTypeLvl1 = new PokemonTypeLvl(new PokemonTypeLvlKey(editedPokemon.getIdPokemon(), type1Id), editedPokemon, type1, 1);
            PokemonTypeLvl pokemonTypeLvl2 = new PokemonTypeLvl(new PokemonTypeLvlKey(editedPokemon.getIdPokemon(), type2Id), editedPokemon, type2, 2);

            pokemonTypeLvlRepository.save(pokemonTypeLvl1);
            pokemonTypeLvlRepository.save(pokemonTypeLvl2);

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

    public List<Pokemon> getDeletedPokemonWithTypes() {
        List<Pokemon> deletedPokemonList = pokemonRepository.findAll();

        deletedPokemonList = deletedPokemonList.stream()
                .filter(pokemon -> !pokemon.getIsActive())
                .collect(Collectors.toList());

        for (Pokemon deletedPokemon : deletedPokemonList) {
            List<PokemonTypeLvl> typeLevels = pokemonTypeLvlRepository.findByPokemon(deletedPokemon);
            if (!typeLevels.isEmpty()) {
                TypePokemon firstType = typeLevels.get(0).getTypePokemon();
                deletedPokemon.setFirstType(firstType != null ? firstType : null);

                if (typeLevels.size() > 1) {
                    TypePokemon secondType = typeLevels.get(1).getTypePokemon();
                    deletedPokemon.setSecondType(secondType != null ? secondType : null);
                } else {
                    deletedPokemon.setSecondType(null);
                }
            } else {
                deletedPokemon.setFirstType(null);
                deletedPokemon.setSecondType(null);
            }
        }
        return deletedPokemonList;
    }

    public void reactivatePokemon(Integer id) {
        try {
            Pokemon pokemonToReactivate = getPokemonById(id);
            pokemonToReactivate.setIsActive(true);
            pokemonRepository.save(pokemonToReactivate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
