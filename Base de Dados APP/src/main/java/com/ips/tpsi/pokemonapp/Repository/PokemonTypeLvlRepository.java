package com.ips.tpsi.pokemonapp.Repository;

import com.ips.tpsi.pokemonapp.entity.Pokemon;
import com.ips.tpsi.pokemonapp.entity.PokemonTypeLvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonTypeLvlRepository extends JpaRepository<PokemonTypeLvl, Integer> {
    List<PokemonTypeLvl> findByPokemon(Pokemon pokemon);
}