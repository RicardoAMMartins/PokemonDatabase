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

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonTypeLvl> typeLevels;

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Transient
    private TypePokemon firstType;

    @Transient
    private TypePokemon secondType;

    public TypePokemon getFirstType() {
        return firstType;
    }

    public TypePokemon getSecondType() {
        return secondType;
    }

    public void setFirstType(TypePokemon firstType) {
        if (this.typeLevels == null) {
            this.typeLevels = new ArrayList<>();
        }

        if (this.typeLevels.size() > 0) {
            this.typeLevels.get(0).setTypePokemon(firstType);
        } else {
            PokemonTypeLvl typeLvl = new PokemonTypeLvl();
            typeLvl.setPokemon(this);
            typeLvl.setTypePokemon(firstType);
            this.typeLevels.add(typeLvl);
        }
    }

    public void setSecondType(TypePokemon secondType) {
        if (this.typeLevels == null) {
            this.typeLevels = new ArrayList<>();
        }

        if (this.typeLevels.size() > 1) {
            this.typeLevels.get(1).setTypePokemon(secondType);
        } else {
            PokemonTypeLvl typeLvl = new PokemonTypeLvl();
            typeLvl.setPokemon(this);
            typeLvl.setTypePokemon(secondType);
            this.typeLevels.add(typeLvl);
        }
    }

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

