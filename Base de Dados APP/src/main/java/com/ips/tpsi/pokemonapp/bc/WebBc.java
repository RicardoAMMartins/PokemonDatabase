    package com.ips.tpsi.pokemonapp.bc;

    import com.ips.tpsi.pokemonapp.Repository.PokemonRepository;
    import com.ips.tpsi.pokemonapp.entity.Pokemon;
    import com.ips.tpsi.pokemonapp.entity.PokemonTypeLvl;
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

        // ... restante do código ...


        public void addPokemon(Pokemon newPokemon) {
            try {
                pokemonRepository.save(newPokemon);
            } catch (Exception e) {
                e.printStackTrace(); // Log or print the exception for debugging
                // Rethrow or handle the exception as needed
            }
        }


        public void editPokemon(Pokemon editedPokemon) {
            pokemonRepository.save(editedPokemon);

        }

        public void deletePokemon(Integer idPokemon) {
            Pokemon pokemonToDelete = getPokemonById(idPokemon);
            pokemonToDelete.setIsActive(false);
            pokemonRepository.save(pokemonToDelete);
        }




        public Pokemon getPokemonById(Integer id) {
            Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
            return optionalPokemon.orElse(null);
        }


    }
