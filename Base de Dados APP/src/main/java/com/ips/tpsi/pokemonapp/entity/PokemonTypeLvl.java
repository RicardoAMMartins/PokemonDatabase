package com.ips.tpsi.pokemonapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Id
    @Column(name = "pokemon_type_level")
    private Integer pokemonTypeLevel;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id_pokemon", nullable = false, foreignKey = @ForeignKey(name = "fk_pokemon_type_level_pokemon"))
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id_type", nullable = false, foreignKey = @ForeignKey(name = "fk_pokemon_type_level_type"))
    private TypePokemon typePokemon;
}
