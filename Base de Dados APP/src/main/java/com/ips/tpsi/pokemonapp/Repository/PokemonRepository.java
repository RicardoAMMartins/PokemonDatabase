package com.ips.tpsi.pokemonapp.Repository;

import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    //Pokemon PokemonfindByName(String name);
    
}
