package com.ips.tpsi.pokemonapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon")
    private Integer idPokemon;

    @Column(name = "name_pokemon")
    private String namePokemon;

    @Column(name = "total")
    private Integer total;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    @Column(name = "super_attack")
    private Integer superAttack;

    @Column(name = "super_defense")
    private Integer superDefense;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "generation")
    private Integer generation;

    @Column(name = "legendary")
    private String legendary;

    @Transient
    private Boolean isActive = true;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @ManyToOne
    @JoinColumn(name = "first_type_id", referencedColumnName = "id_type", foreignKey = @ForeignKey(name = "fk_pokemon_first_type"))
    private TypePokemon firstType;

    @ManyToOne
    @JoinColumn(name = "second_type_id", referencedColumnName = "id_type", foreignKey = @ForeignKey(name = "fk_pokemon_second_type"))
    private TypePokemon secondType;

    // ... outros métodos ...

    // Método de conversão DTO para evitar ciclos infinitos durante a serialização
    public PokemonDTO toDTO() {
        PokemonDTO dto = new PokemonDTO();
        // Copie os campos relevantes para o DTO
        dto.setIdPokemon(this.idPokemon);
        // ... outros campos ...
        return dto;
    }
}

