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

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private Boolean isActive;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonTypeLvl> typeLevels;

    @Transient
    private TypePokemon firstType;

    @Transient
    private TypePokemon secondType;


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

    public PokemonDTO toDTO() {
        PokemonDTO dto = new PokemonDTO();
        dto.setIdPokemon(this.idPokemon);
        return dto;
    }
}

