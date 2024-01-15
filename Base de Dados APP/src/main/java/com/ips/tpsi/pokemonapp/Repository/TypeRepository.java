package com.ips.tpsi.pokemonapp.Repository;

import com.ips.tpsi.pokemonapp.entity.TypePokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TypeRepository extends JpaRepository<TypePokemon, Integer> {

}
