package com.ips.tpsi.pokemonapp.Repository;

import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ips.tpsi.pokemonapp.entity.TypePokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    //Pokemon PokemonfindByName(String name);

}
