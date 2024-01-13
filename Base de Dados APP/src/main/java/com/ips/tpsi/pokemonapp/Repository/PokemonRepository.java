package com.ips.tpsi.pokemonapp.Repository;

import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    //Pokemon PokemonfindByName(String name);
    
}
