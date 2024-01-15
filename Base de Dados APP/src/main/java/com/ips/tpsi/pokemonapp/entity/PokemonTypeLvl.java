package com.ips.tpsi.pokemonapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemontypelvl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class PokemonTypeLvl {

    @EmbeddedId
    private PokemonTypeLvlKey id;

    @ManyToOne
    @MapsId("pokemonId")
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne
    @MapsId("typeId")
    @JoinColumn(name = "type_id")
    private TypePokemon typePokemon;

    @Column(name = "pokemon_type_level")
    private Integer pokemonTypeLevel;
}